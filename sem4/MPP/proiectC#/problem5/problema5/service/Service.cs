using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using model;
using problema5.repository;

namespace service;

public class Service
{
    private ChildDbRepository childRepo;
    private UserDbRepository userRepo;
    private TaskDbRepository taskRepo;
    private ParticipationDbRepository participationRepo;

    public Service(ChildDbRepository childRepo, UserDbRepository userRepo, TaskDbRepository taskRepo, ParticipationDbRepository participationRepo) {
        this.childRepo = childRepo;
        this.userRepo = userRepo;
        this.taskRepo = taskRepo;
        this.participationRepo = participationRepo;
    }

    public User findUser(String username){
        return userRepo.findByEmail(username);
    }

    public IEnumerable<TaskDTO> findAllTasks()
    {
        IEnumerable<TaskDTO> tasks = new List<TaskDTO>();
        foreach (var t in taskRepo.FindAll())
        {
            TaskDTO task = new TaskDTO(t, this.numberOfParticipants(t));
            tasks.Append(task);
        }
        return tasks;
    }

    public Participation addParticipation(String name, int age, Task task){
        Child child = new Child(name, age);
        childRepo.Save(child);
        child.ID = maxIdChild();
        Participation participation = new Participation(child, task);
        participationRepo.Save(participation);
        return participation;
    }

    public IEnumerable<Child> findParticipants(Task task){
        IEnumerable<Participation> participations = participationRepo.findByTask(task);
        IList<Child> children = new List<Child>();
        foreach (Participation p in participations)
        {
            Child child = p.child;
            children.Add(child);
        }
        return children;
    }

    public int numberOfParticipants(Task task){
        int no = 0;
        foreach (Participation p in participationRepo.findByTask(task)) 
            no++;
        return no;
    }

    public int maxIdChild(){
        IEnumerable<Child> children = childRepo.FindAll();
        int max = 0;
        foreach(Child child in children)
            if(max < child.ID)
                max = child.ID;
        return max;
    }
}