package org.example.service;

import org.example.model.*;

public interface ICompetitionServices {
    User login(User user, ICompetitionObserver client) throws CompetitionException;
    TaskDTO[] findAllTasks() throws CompetitionException;
    Child[] findByTask(Task task) throws CompetitionException;
    void addParticipation(Participation participation) throws CompetitionException;
    void addChild(Child kid) throws CompetitionException;
    Child findLastAdded() throws CompetitionException;
    void logout(User user, ICompetitionObserver client) throws CompetitionException;
    void updateEvent() throws CompetitionException;
}
