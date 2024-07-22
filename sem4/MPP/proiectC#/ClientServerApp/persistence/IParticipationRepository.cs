using System.Collections.Generic;
using model;

namespace repository;

public interface IParticipationRepository : Repository<int, Participation>
{
    IEnumerable<Participation> findByTask(Task task);
}