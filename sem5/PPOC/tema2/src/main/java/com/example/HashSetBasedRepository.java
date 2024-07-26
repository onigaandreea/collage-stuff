package com.example;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashSetBasedRepository<T> implements InMemoryRepository<T>{
    private final Set<T> set;

    public HashSetBasedRepository() {
        this.set = new HashSet<>();
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

    public void addAll(Collection<T> set){
        this.set.addAll(set);
    }
}
