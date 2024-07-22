package org.example.service;

import org.example.model.Participation;

public interface ICompetitionObserver {
    void updateEvents() throws CompetitionException;
}
