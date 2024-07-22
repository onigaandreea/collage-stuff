using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using model;

namespace services
{
    public interface ICompetitionServices
    
    {
        void login(User user, ICompetitionObserver client);
        List<TaskDTO> findAllTasks();
        List<Child> findByTask(Task task);
        void addParticipation(Participation participation);
        void addChild(Child child);
        Child findLastAdded();
        
        void logout(User user, ICompetitionObserver client);
        void updateEvent();

    }
}
