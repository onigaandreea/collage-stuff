package com.example;

import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Sets;

import java.util.Collection;

public class EclipseBasedRepository<T> implements InMemoryRepository<T>{

    private MutableSet<T> set;

    public EclipseBasedRepository() {
        this.set = Sets.mutable.empty();
    }


    @Override
    public void add(T elem) {
        this.set.add(elem);
    }

    @Override
    public boolean contains(T elem) {
        return this.set.contains(elem);
    }

    @Override
    public void remove(T elem) {
        this.set.remove(elem);
    }

    public void clear(){
        this.set.clear();
    }

    public void addAll(Collection<T> elems){
        this.set.addAll(elems);
    }
}
