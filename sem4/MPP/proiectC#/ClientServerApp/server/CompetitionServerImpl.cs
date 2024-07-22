using System.Collections.Generic;
using System.Linq;
using System;
using model;
using repository;
using services;
using Task = System.Threading.Tasks.Task;

namespace server
{
    public class CompetitionServerImpl: ICompetitionServices
    {
        private IUserRepository userRepository;
        private readonly IDictionary <String, ICompetitionObserver> loggedClients;

        public CompetitionServerImpl(IUserRepository repo) {
        userRepository = repo;
        loggedClients=new Dictionary<String, ICompetitionObserver>();
    }

    public  void login(User user, ICompetitionObserver client)  {
        User userOk=userRepository.findByEmail(user.username);
        if (userOk!=null){
            if(loggedClients.ContainsKey(user.ID.ToString()))
                throw new CompetitionException("User already logged in.");
            loggedClients[user.ID.ToString()]= client;
        }else
            throw new CompetitionException("Authentication failed.");
    }

    public User findUser(string username)
    {
        throw new NotImplementedException();
    }

    public void search(model.Task task)
    {
        throw new NotImplementedException();
    }

    public void enrol(Participation participation, ICompetitionObserver participant)
    {
        throw new NotImplementedException();
    }

    public  void logout(User user, ICompetitionObserver client) {
        ICompetitionObserver localClient=loggedClients[user.ID.ToString()];
        if (localClient==null)
            throw new CompetitionException("User "+user.ID+" is not logged in.");
        loggedClients.Remove(user.ID.ToString());
    }

    public int numberOfParticipants(model.Task value)
    {
        throw new NotImplementedException();
    }

    public IEnumerable<model.Task> findAllTasks()
    {
        throw new NotImplementedException();
    }

    public IEnumerable<Child> findByTask(model.Task task)
    {
        throw new NotImplementedException();
    }

    public void addParticipation(string name, int age, model.Task task)
    {
        throw new NotImplementedException();
    }
    }
}
