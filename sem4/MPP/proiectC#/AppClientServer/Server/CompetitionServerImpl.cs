using System.Collections.Generic;
using System.Linq;
using System;
using model;
using repository;
using services;

namespace server
{
    public class CompetitionServerImpl: ICompetitionServices
    {
        private IUserRepository userRepository;
        private IChildRepository childRepository;
        private IParticipationRepository participationRepository;
        private ITaskRepository taskRepository;
        private readonly IDictionary <String, ICompetitionObserver> loggedClients;

        public CompetitionServerImpl(IUserRepository userrepo, ITaskRepository taskrepo, IChildRepository childrepo, IParticipationRepository participationrepo) {
        userRepository = userrepo;
        taskRepository = taskrepo;
        childRepository = childrepo;
        participationRepository = participationrepo;
        loggedClients=new Dictionary<String, ICompetitionObserver>();
    }

    public void login(User user, ICompetitionObserver client)  {
        User userOk=userRepository.findByEmail(user.username);
        if (userOk!=null){
            if(loggedClients.ContainsKey(user.username))
                throw new CompetitionException("User already logged in.");
            loggedClients[user.username]= client;
        }else
            throw new CompetitionException("Authentication failed.");
    }

    public int nrParticipants(Task task)
    {
        return participationRepository.findByTask(task).Count();
    }
    
    public List<TaskDTO> findAllTasks()
    {
        List<TaskDTO> tasks = new List<TaskDTO>();
        foreach (Task task in taskRepository.FindAll())
        {
            TaskDTO entity = new TaskDTO(task.description, task.ageCatStart, task.ageCatEnd, nrParticipants(task));
            entity.ID = task.ID;
            tasks.Add(entity);
        }

        return tasks;
    }

    public List<Child> findByTask(Task task)
    {
        List<Child> children = new List<Child>();
        foreach (Participation part in participationRepository.findByTask(task))
        {
            children.Add(part.child);
        }

        return children;
    }

    public void addParticipation(Participation participation)
    {
        Child child = participation.child;
        foreach(Participation p in participationRepository.findByTask(participation.task)){
            if(child == p.child)
                throw new CompetitionException("This child is already enrolled at this task!");
        }
        participationRepository.Save(participation);
    }

    public void addChild(Child child)
    {
        childRepository.Save(child);
    }

    public Child findLastAdded()
    {
        List<Child> kids = childRepository.FindAll();
        return kids[kids.Count() - 1];
    }

    public  void logout(User user, ICompetitionObserver client) {
        ICompetitionObserver localClient=loggedClients[user.username];
        if (localClient==null)
            throw new CompetitionException("User was not logged in.");
        loggedClients.Remove(user.username);
    }

    public void updateEvent()
    {
        foreach (String email in loggedClients.Keys)
        {
            Console.WriteLine($"Updating: {email} ");
            ICompetitionObserver Client = loggedClients[email];
            if (Client != null)
            {
                System.Threading.Tasks.Task.Run(() => Client.updateEvents());
            }
        }
    }
    }
}
