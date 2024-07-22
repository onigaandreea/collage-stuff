package org.example;

import org.example.model.*;
import org.example.service.CompetitionException;
import org.example.service.ICompetitionObserver;
import org.example.service.ICompetitionServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CompetitionServiceProxy implements ICompetitionServices {
    private String host;
    private int port;

    private ICompetitionObserver client;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;
    public CompetitionServiceProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<Response>();
    }

    public User login(User user, ICompetitionObserver client) throws CompetitionException {
        initializeConnection();
        Request req = new Request.Builder().type(RequestType.LOGIN).data(user).build();
        sendRequest(req);
        Response response = readResponse();
        if(response.type() == ResponseType.ERROR){
            String err = response.data().toString();
            closeConnection();
            throw new CompetitionException(err);
        }
        this.client = client;
        User found = (User) response.data();
        return found;
    }

    @Override
    public TaskDTO[] findAllTasks() throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.GET_TASKS).data(null).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
        TaskDTO[] foundE = (TaskDTO[]) resp.data();
        System.out.println("PROXY: Found tasks" + Arrays.toString(foundE));
        return foundE;
    }

    @Override
    public Child[] findByTask(Task task) throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.GET_PARTICIPANTS).data(task).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
        Child[] foundE = (Child[])resp.data();
        System.out.println("PROXY: Found participants" + Arrays.toString(foundE));
        return foundE;
    }

    @Override
    public void addParticipation(Participation participation) throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.ADD_PARTICIPATION).data(participation).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
    }

    @Override
    public void addChild(Child kid) throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.ADD_CHILD).data(kid).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
    }

    @Override
    public Child findLastAdded() throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.FIND_LAST_CHILD).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
        Child c = (Child) resp.data();
        System.out.println("PROXY: Found last swimmer" + c);
        return c;
    }

    @Override
    public void logout(User user, ICompetitionObserver client) throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(user).build();
        sendRequest(req);
        Response resp = readResponse();
        closeConnection();
        if(resp.type() == ResponseType.ERROR){
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
    }

    @Override
    public void updateEvent() throws CompetitionException {
        Request req = new Request.Builder().type(RequestType.UPDATE).build();
        sendRequest(req);
        Response resp = readResponse();
        if(resp.type() == ResponseType.ERROR) {
            String err = resp.data().toString();
            throw new CompetitionException(err);
        }
    }

    private void handleUpdate(Response response) throws CompetitionException{
        if (response.type()== ResponseType.UPDATE){
            try {
                client.updateEvents();
            } catch (CompetitionException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void closeConnection() {
        finished=true;
        try {
            input.close();
            output.close();
            connection.close();
            client=null;
        } catch (IOException e) {
            System.out.println("PROXY: Error on closeConnection: "+ e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendRequest(Request request)throws CompetitionException {
        try {
            output.writeObject(request);
            output.flush();
        } catch (IOException e) {
            throw new CompetitionException("Error sending object "+e);
        }

    }

    private Response readResponse() throws CompetitionException {
        Response response=null;
        try{
            response=qresponses.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }
    private void initializeConnection() throws CompetitionException {
        try {
            connection=new Socket(host,port);
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            finished=false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startReader(){
        Thread tw=new Thread(new ReaderThread());
        tw.start();
    }

    private boolean isUpdate(Response response){
        return response.type()== ResponseType.UPDATE;
    }
    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    Object response=input.readObject();
                    System.out.println("response received "+response);
                    if (isUpdate((Response)response)){
                        handleUpdate((Response)response);
                    }else{
                        try {
                            qresponses.put((Response)response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException | CompetitionException e) {
                    System.out.println("Reading error "+e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }
}
