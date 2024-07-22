package org.example;

import org.example.model.*;
import org.example.persistence.IChildRepository;
import org.example.persistence.IParticipationRepository;
import org.example.persistence.ITaskRepository;
import org.example.persistence.IUserRepository;
import org.example.service.CompetitionException;
import org.example.service.ICompetitionObserver;
import org.example.service.ICompetitionServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.StreamSupport;

public class CompetitionServicesImpl implements ICompetitionServices {
    private IUserRepository userRepository;
    private ITaskRepository taskRepository;
    private IChildRepository childRepository;
    private IParticipationRepository participationRepository;

    private Map<String, ICompetitionObserver> loggedClients;

    public CompetitionServicesImpl(IUserRepository userRepository, ITaskRepository taskRepository, IChildRepository childRepository, IParticipationRepository participationRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.childRepository = childRepository;
        this.participationRepository = participationRepository;
        this.loggedClients = new ConcurrentHashMap<>();
    }

    public synchronized User login(User user, ICompetitionObserver client) throws CompetitionException {
        String email = user.getUsername();
        String password = user.getPassword();

        User foundUser = userRepository.findByEmail(email);
        if (foundUser == null) {
            throw new CompetitionException("It doesn't exist a person with this email!");
        }
        else if(!foundUser.getPassword().equals(password)) {
            throw new CompetitionException("Incorrect password!");
        }
        else if(loggedClients.get(foundUser.getUsername()) != null) {
            throw new CompetitionException("Client already logged in!");
        }
        loggedClients.put(foundUser.getUsername(), client);
        return foundUser;
    }


    public Integer numberOfParticipants(Task value) {
        List<Participation> childrenForTask = StreamSupport.stream(participationRepository.findByTask(value).spliterator(), false).toList();
        return childrenForTask.size();
    }

    @Override
    public TaskDTO[] findAllTasks() {
        List<TaskDTO> tasksList = new ArrayList<>();
        for(Task t: taskRepository.findAll()){
            TaskDTO entity = new TaskDTO(t.getDescription(),t.getAgeCatStart(),t.getAgeCatEnd(),numberOfParticipants(t));
            entity.setId(t.getId());
            tasksList.add(entity);
        }
        return tasksList.stream().toArray(TaskDTO[]::new);
    }

    @Override
    public Child[] findByTask(Task task) {
        List<Child> children = new ArrayList<>();
        for(Participation p: participationRepository.findByTask(task))
            children.add(p.getChild());
        return children.toArray(Child[]::new);
    }

    @Override
    public synchronized void addParticipation(Participation participation) throws CompetitionException{
        Child child = participation.getChild();
        for(Child c: findByTask(participation.getTask())){
            if(child.equals(c))
                throw new CompetitionException("This child is already enrolled at this task!");
        }
        participationRepository.add(participation);
    }

    @Override
    public void addChild(Child kid) throws CompetitionException {
        childRepository.add(kid);
    }

    @Override
    public Child findLastAdded() throws CompetitionException {
        List<Child> kids = (List<Child>) childRepository.findAll();
        return kids.get(kids.size() - 1);
    }

    @Override
    public synchronized void logout(User user, ICompetitionObserver client) throws CompetitionException {
        ICompetitionObserver c = loggedClients.get(user.getUsername());
        if(c == null) {
            throw new CompetitionException("User was not logged in!");
        }
    }

    @Override
    public void updateEvent() throws CompetitionException {
        notifyUsers();
    }

    private synchronized void notifyUsers() {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for(String email: loggedClients.keySet()) {
            ICompetitionObserver client = loggedClients.get(email);
            if(client != null) {
                executor.execute(() -> {
                    try {
                        System.out.println("Notifying user: " + email);
                        client.updateEvents();
                    } catch(CompetitionException ex) {
                        System.err.println("Error at notify: "+ ex.getMessage());
                    }
                });
            }
        }
        executor.shutdown();
    }
}
