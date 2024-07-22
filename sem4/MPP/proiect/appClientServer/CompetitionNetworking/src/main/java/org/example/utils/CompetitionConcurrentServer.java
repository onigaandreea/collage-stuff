package org.example.utils;


import org.example.CompetitionClientWorker;
import org.example.service.ICompetitionServices;

import java.net.Socket;


public class CompetitionConcurrentServer extends AbsConcurrentServer {
    private ICompetitionServices competitionServer;
    public CompetitionConcurrentServer(int port, ICompetitionServices chatServer) {
        super(port);
        this.competitionServer = chatServer;
        System.out.println("Competition- CompetitionObjectConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
        CompetitionClientWorker worker=new CompetitionClientWorker(competitionServer, client);
        Thread tw=new Thread(worker);
        return tw;
    }
    @Override
    public void stop(){
        System.out.println("Stopping services ...");
    }
}
