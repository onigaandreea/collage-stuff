using System.Collections.Generic;
using model;

namespace problema5.repository
{
    public interface Repository<ID,E> where E : Entity<ID>
    {
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        E Save(E entity);
        E Delete(ID id);
        E Update(E entity);
    }
}

