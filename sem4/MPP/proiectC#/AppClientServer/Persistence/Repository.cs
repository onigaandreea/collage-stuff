using System.Collections.Generic;
using model;

namespace repository
{
    public interface Repository<ID,E> where E : Entity<ID>
    {
        E FindOne(ID id);
        List<E> FindAll();
        E Save(E entity);
        E Delete(ID id);
        E Update(E entity);
    }
}

