package repository.memory;

import domain.Entity;
import domain.validators.ValidationException;
import domain.validators.Validator;
import repository.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InMemoryRepository<ID,E extends Entity<ID>> implements Repository<ID,E> {
    private final Validator<E> validator;
    private final HashMap<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }

    @Override
    public E findOne(ID id){
        if (id == null)
            throw new IllegalArgumentException("id must be not null");
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) throws ValidationException {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");

        this.validator.validate(entity);

        if(entities.get(entity.getId()) != null) {
            return entity;
        }
        if (this.entities.containsKey(entity.getId()))
        {
            return entity;
        }
        else entities.put(entity.getId(),entity);
        return null;
    }

    @Override
    public E remove(ID id) {
        return null;
    }

    @Override
    public E update(E entity) throws ValidationException {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        this.validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return null;
        }
        return entity;
    }

    @Override
    public int size(){
        return this.entities.size();
    }

}
