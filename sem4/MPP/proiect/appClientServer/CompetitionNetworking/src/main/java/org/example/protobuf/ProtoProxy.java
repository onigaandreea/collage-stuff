package org.example.protobuf;

import org.example.model.*;
import org.example.service.*;

import java.io.*;
import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProtoProxy implements ICompetitionServices {
    private String host;
    private int port;
    private ICompetitionObserver client;
    private InputStream input;
    private OutputStream output;
    private Socket connection;
    private BlockingQueue<CompetitionProtobufs.Response> qresponses;
    private volatile boolean finished;
    public ProtoProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses=new LinkedBlockingQueue<CompetitionProtobufs.Response>();
    }
    @Override
    public User login(User user, ICompetitionObserver client) throws CompetitionException {
        initializeConnection();
        System.out.println("Login request ...");
        sendRequest(ProtoUtils.createLoginRequest(user));
        CompetitionProtobufs.Response response=readResponse();
        if (response.getType()==CompetitionProtobufs.Response.Type.Login){
            this.client=client;
            return user;
        }
        if (response.getType()==CompetitionProtobufs.Response.Type.Error){
            String errorText=ProtoUtils.getError(response);
            closeConnection();
            throw new CompetitionException(errorText);
        }
        return null;
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

    private void initializeConnection() throws CompetitionException {
        try {
            connection=new Socket(host,port);
            output=connection.getOutputStream();
            //output.flush();
            input=connection.getInputStream();
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

    private class ReaderThread implements Runnable{
        public void run() {
            while(!finished){
                try {
                    CompetitionProtobufs.Response response=CompetitionProtobufs.Response.parseDelimitedFrom(input);
                    System.out.println("response received "+response);

                    if (isUpdateResponse(response.getType())){
                        handleUpdate(response);
                    }else{
                        try {
                            qresponses.put(response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error "+e);
                }
            }
        }
    }

    private boolean isUpdateResponse(CompetitionProtobufs.Response.Type type){
        switch (type){
            case Update:  return true;
        }
        return false;
    }


    private void handleUpdate(CompetitionProtobufs.Response updateResponse){
        switch (updateResponse.getType()){
            case Update:{
                System.out.println("Update");
                try {
                    client.updateEvents();
                } catch (CompetitionException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void sendRequest(CompetitionProtobufs.Request request)throws CompetitionException{
        try {
            System.out.println("Sending request ..."+request);
            //request.writeTo(output);
            request.writeDelimitedTo(output);
            output.flush();
            System.out.println("Request sent.");
        } catch (IOException e) {
            throw new CompetitionException("Error sending object "+e);
        }
    }

    private CompetitionProtobufs.Response readResponse() throws CompetitionException{
        CompetitionProtobufs.Response response=null;
        try{
            response=qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Child[] findByTask(Task task) throws CompetitionException {
        sendRequest(ProtoUtils.createGetParticipantsRequest(task));
        CompetitionProtobufs.Response response = readResponse();
        if(response.getType() == CompetitionProtobufs.Response.Type.Error) {
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
        Child[] participantDTOS = ProtoUtils.getParticipants(response);
        return participantDTOS;
    }

    @Override
    public void addParticipation(Participation participation) throws CompetitionException {
        System.out.println("ADD PARTICIPATION send request" + participation);
        sendRequest(ProtoUtils.createAddParticipationRequest(participation));
        CompetitionProtobufs.Response response = readResponse();
        if(response.getType() == CompetitionProtobufs.Response.Type.Error) {
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
    }

    @Override
    public void addChild(Child child) throws CompetitionException {
        sendRequest(ProtoUtils.createAddChildRequest(child));
        CompetitionProtobufs.Response response = readResponse();
        if(response.getType() == CompetitionProtobufs.Response.Type.Error) {
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
    }

    @Override
    public Child findLastAdded() throws CompetitionException {
        sendRequest(ProtoUtils.createFindLastSwimmerRequest());
        CompetitionProtobufs.Response response = readResponse();
        if (response.getType() == CompetitionProtobufs.Response.Type.Error){
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
        Child last = ProtoUtils.getLastChild(response);
        return last;
    }

    @Override
    public void logout(User user, ICompetitionObserver client) throws CompetitionException {
        sendRequest(ProtoUtils.createLogoutRequest(user));
        CompetitionProtobufs.Response response = readResponse();
        closeConnection();
        if (response.getType() == CompetitionProtobufs.Response.Type.Error){
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
    }

    @Override
    public void updateEvent() throws CompetitionException {
        sendRequest(ProtoUtils.createUpdateRequest());
        CompetitionProtobufs.Response response = readResponse();
        if(response.getType() == CompetitionProtobufs.Response.Type.Error){
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
    }

    @Override
    public TaskDTO[] findAllTasks() throws CompetitionException {
        sendRequest(ProtoUtils.createGetTasksRequest());
        CompetitionProtobufs.Response response = readResponse();
        if(response.getType() == CompetitionProtobufs.Response.Type.Error){
            String errorText = ProtoUtils.getError(response);
            throw new CompetitionException(errorText);
        }
        TaskDTO[] events = ProtoUtils.getAllTasks(response);
        return events;
    }

}
