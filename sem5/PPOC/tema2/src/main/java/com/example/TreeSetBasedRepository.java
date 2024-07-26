package com.example;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedRepository<T> implements InMemoryRepository<T>{
    private final Set<T> set;

    public TreeSetBasedRepository() {
        this.set = new TreeSet<>();
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
