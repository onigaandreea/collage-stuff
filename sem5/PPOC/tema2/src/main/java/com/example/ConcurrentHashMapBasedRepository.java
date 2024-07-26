package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapBasedRepository<T> implements InMemoryRepository<T>{
    private final Map<T, Integer> map;

    public ConcurrentHashMapBasedRepository() {
        map = new ConcurrentHashMap<>();
    }

    @Override
    public void add(T elem) {
        this.map.put(elem, 1);
    }

    @Override
    public boolean contains(T elem) {
        return this.map.containsKey(elem);
    }

    @Override
    public void remove(T elem) {
        this.map.remove(elem);
    }

    public void clear(){
        this.map.clear();
    }


}
