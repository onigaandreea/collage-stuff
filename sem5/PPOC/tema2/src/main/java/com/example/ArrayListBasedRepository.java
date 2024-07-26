package com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayListBasedRepository<T> implements InMemoryRepository<T>{
    private List<T> list;

    public ArrayListBasedRepository() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T elem) {
        this.list.add(elem);
    }

    @Override
    public boolean contains(T elem) {
        return this.list.contains(elem);
    }

    @Override
    public void remove(T elem) {
        this.list.remove(elem);
    }

    public void clear(){
        this.list.clear();
    }

    public void addAll(Collection<T> list){
        this.list.addAll(list);
    }
}
