using System;
using System.Collections.Generic;
using model;
using services;

namespace Client
{
    public class CompetitionController  : ICompetitionObserver
    {
        public event EventHandler<CompetitionUserEvent> UpdateEvent;
        private ICompetitionServices Server;
        private User crtUser;

        public CompetitionController(ICompetitionServices server)
        {
            this.Server = server;
            this.crtUser = null;
        }
        
        public void login(String username)
        {
            User user = new User();
            user.username = username;
            Server.login(user, this);
            Console.WriteLine("Login succeeded...");
            crtUser = user;
            Console.WriteLine("Current user {0}", user);
        }

        public void logout()
        {
            Console.WriteLine("Controller logout");
            this.Server.logout(this.crtUser, this);
            this.crtUser = null;
        }
        
        public List<TaskDTO> listTasks()
        {
            Console.WriteLine("List tasks");
            return this.Server.findAllTasks();
        }

        public List<Child> listParticipants(Task task)
        {
            Console.WriteLine("List participants");
            return this.Server.findByTask(task);
        }
        
        public void addChild(Child child)
        {
            Console.WriteLine("Add child");
            this.Server.addChild(child);
        }

        public void addParticipant(String name, int age, Task task)
        {
            Child child = new Child(name, age);
            addChild(child);
            Child toAdd = Server.findLastAdded();
            Participation participation = new Participation(toAdd, task);
            Console.WriteLine("Add participant");
            Server.addParticipation(participation);
        }

        protected virtual void OnUserEvent(CompetitionUserEvent userEvent)
        {
            if (UpdateEvent == null)
            {
                return;
            }

            UpdateEvent(this, userEvent);
            Console.WriteLine("Update Event called");
        }
        
        public void updateEvents()
        {
            Console.WriteLine("Participant added, update lists");
            CompetitionUserEvent userEvent = new CompetitionUserEvent(UserEvent.ParticipantAdded, null);
            this.OnUserEvent(userEvent);
        }
    }
}