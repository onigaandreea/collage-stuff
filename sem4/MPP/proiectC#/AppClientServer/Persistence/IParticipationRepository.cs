using System.Collections.Generic;
using model;

namespace repository;

public interface IParticipationRepository : Repository<int, Participation>
{
    List<Participation> findByTask(Task task);
}