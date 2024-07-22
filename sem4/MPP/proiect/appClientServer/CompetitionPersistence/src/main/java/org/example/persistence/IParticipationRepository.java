package org.example.persistence;

import org.example.model.Participation;
import org.example.model.Task;

public interface IParticipationRepository extends Repository<Participation,Integer> {
    Iterable<Participation> findByTask(Task task);
}
