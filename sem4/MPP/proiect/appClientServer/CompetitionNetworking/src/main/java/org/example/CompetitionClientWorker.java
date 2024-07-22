package org.example;

import org.example.model.*;
import org.example.service.CompetitionException;
import org.example.service.ICompetitionObserver;
import org.example.service.ICompetitionServices;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CompetitionClientWorker implements Runnable, ICompetitionObserver {
    private ICompetitionServices server;
    private Socket connection;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;
    public CompetitionClientWorker(ICompetitionServices server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try{
            output=new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input=new ObjectInputStream(connection.getInputStream());
            connected=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(connected){
            try {
                Object request = input.readObject();
                System.out.println("WORKER Request: " + request);

                Response response = handleRequest((Request)request);
                if (response != null){
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error "+e);
        }
    }

    private Response handleRequest(Request request){
        Response response = null;
        if (request.type() == RequestType.LOGIN){
            System.out.println("Login request ..."  + request.type());
            User user = (User) request.data();
            try {
                User foundUser = server.login(user, this);
                return new Response.Builder().type(ResponseType.LOGIN).data(foundUser).build();
            } catch (CompetitionException e) {
                connected = false;
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if (request.type() == RequestType.GET_TASKS) {
            System.out.println("GET_Tasks request..." + request.type());
            try {
                TaskDTO[] found = server.findAllTasks();
                return new Response.Builder().type(ResponseType.GET_TASKS).data(found).build();
            } catch (CompetitionException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }

        if(request.type() == RequestType.ADD_CHILD) {
            System.out.println("ADD_CHILD request..." + request.type());
            Child child = (Child) request.data();
            try {
                server.addChild(child);
                return new Response.Builder().type(ResponseType.ADD_CHILD).build();
            } catch (CompetitionException ex) {
                return new Response.Builder().type(ResponseType.ERROR).data(ex.getMessage()).build();
            }

        }

        if(request.type() == RequestType.FIND_LAST_CHILD) {
            System.out.println("FIND_LAST_CHILD request..." + request.type());
            try {
                Child s = server.findLastAdded();
                return new Response.Builder().type(ResponseType.FIND_LAST_CHILD).data(s).build();
            } catch(CompetitionException ex) {
                return new Response.Builder().type(ResponseType.ERROR).data(ex.getMessage()).build();
            }
        }

        if (request.type() == RequestType.GET_PARTICIPANTS) {
            System.out.println("GET_PARTICIPANTS request..." + request.type());
            Task task = (Task) request.data();
            try {
                Child[] found = server.findByTask(task);
                return new Response.Builder().type(ResponseType.GET_PARTICIPANTS).data(found).build();
            } catch (CompetitionException e) {
                return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
            }
        }
        if(request.type() == RequestType.ADD_PARTICIPATION) {
            System.out.println("ADD_PARTICIPATION request..." + request.type());
            Participation participation = (Participation) request.data();
            try {
                server.addParticipation(participation);
                return new Response.Builder().type(ResponseType.ADD_PARTICIPATION).build();
            } catch (CompetitionException ex) {
                return new Response.Builder().type(ResponseType.ERROR).data(ex.getMessage()).build();
            }
        }
        if(request.type() == RequestType.LOGOUT){
            System.out.println("LOGOUT request: "+ request.type());
            User user = (User)request.data();
            try{
                server.logout(user, this);
                connected = false;
                return new Response.Builder().type(ResponseType.OK).data(null).build();
            }catch (CompetitionException ex){
                return new Response.Builder().type(ResponseType.ERROR).data(ex.getMessage()).build();
            }
        }
        if(request.type() == RequestType.UPDATE) {
            System.out.println("UPDATE request: " + request.type());
            try {
                server.updateEvent();
                return new Response.Builder().type(ResponseType.OK).build();
            } catch (CompetitionException ex) {
                return new Response.Builder().type(ResponseType.ERROR).data(ex.getMessage()).build();
            }
        }
        return response;
    }

    private void sendResponse(Response response) throws IOException{
        System.out.println("sending response "+response);

        output.writeObject(response);
        output.flush();
    }

    @Override
    public void updateEvents() {
        Response resp = new Response.Builder().type(ResponseType.UPDATE).build();
        try {
            sendResponse(resp);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
