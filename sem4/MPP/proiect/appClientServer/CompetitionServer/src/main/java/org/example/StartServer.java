package org.example;

import org.example.model.User;
import org.example.persistence.IChildRepository;
import org.example.persistence.IParticipationRepository;
import org.example.persistence.ITaskRepository;
import org.example.persistence.IUserRepository;
import org.example.persistence.hibernate.UserHibernate;
import org.example.persistence.repository.ChildDBRepository;
import org.example.persistence.repository.ParticipationDBRepository;
import org.example.persistence.repository.TaskDBRepository;
import org.example.persistence.repository.UserDBRepository;
import org.example.service.ICompetitionServices;
import org.example.utils.AbstractServer;
import org.example.utils.CompetitionConcurrentServer;
import org.example.utils.ServerException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

public class StartServer {
    private static int defaultPort=55555;
    public static void main(String[] args) {
        // UserRepository userRepo=new UserRepositoryMock();
        Properties serverProps=new Properties();
        try {
            serverProps.load(StartServer.class.getResourceAsStream("/competitionserver.properties"));
            System.out.println("Server properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find competitionserver.properties "+e);
            return;
        }
        //IUserRepository userRepo = new UserDBRepository(serverProps);
        IUserRepository userRepo = new UserHibernate();
        ITaskRepository taskRepository = new TaskDBRepository(serverProps);
        IChildRepository childRepository = new ChildDBRepository(serverProps);
        IParticipationRepository participationRepository = new ParticipationDBRepository(serverProps,childRepository,taskRepository);
        ICompetitionServices competitionServerImpl=new CompetitionServicesImpl(userRepo,taskRepository,childRepository,participationRepository);

//        Iterable<User> allUsers = userRepoHibernate.findAll();
//        for (User user: allUsers) {
//            System.out.println(user);
//        }

        int competitionServerPort=defaultPort;
        try {
            competitionServerPort = Integer.parseInt(serverProps.getProperty("competition.server.port"));
        }catch (NumberFormatException nef){
            System.err.println("Wrong  Port Number"+nef.getMessage());
            System.err.println("Using default port "+defaultPort);
        }
        System.out.println("Starting server on port: "+competitionServerPort);
        AbstractServer server = new CompetitionConcurrentServer(competitionServerPort, competitionServerImpl);
        try {
            server.start();
        } catch (ServerException e) {
            System.err.println("Error starting the server" + e.getMessage());
        }
        finally {
            try {
                server.stop();
            } catch (ServerException e) {
                System.out.println("Error stopping server: " + e.getMessage());
            }
        }
    }
}
