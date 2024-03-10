package repository;

import domain.Entity;
import domain.validators.ValidationException;
import domain.validators.Validator;

import java.util.HashMap;
import java.util.Set;

public class InMemoryRepository<ID,E extends Entity<ID>> implements Repository<ID,E> {
    private final Validator<E> validator;
    private final HashMap<ID,E> entities;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<ID,E>();
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
    public E remove(ID id)
    {
        return this.entities.remove(id);
    }

    @Override
    public E update(E entity) throws IllegalArgumentException, ValidationException
    {
        if (entity == null)
        {
            throw new IllegalArgumentException("entity must not be null");
        }

        this.validator.validate(entity);

        if (this.entities.containsKey(entity.getId()))
        {
            this.entities.put(entity.getId(), entity);
            return null;
        }

        return entity;
    }

    @Override
    public long size(){
        return (long)this.entities.size();
    }

    @Override
    public E getLast(){
        int size = this.entities.size();
        return this.entities.get(size);
    }
}
