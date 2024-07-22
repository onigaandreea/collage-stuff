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
        User findUser(String username);
        void search(Task task);
        void enrol(Participation participation, ICompetitionObserver participant);
        void logout(User user, ICompetitionObserver client);
        int numberOfParticipants(Task value);

        IEnumerable<Task> findAllTasks();

        IEnumerable<Child> findByTask(Task task);

        void addParticipation(String name, int age, Task task);

    }
}
