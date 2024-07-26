package com.example;

import com.koloboke.collect.set.ObjSet;
import com.koloboke.collect.set.hash.HashObjSets;

import java.util.Collection;

public class KolobokeBasedRepository<T> implements InMemoryRepository<T> {

    private ObjSet<T> set;

    public KolobokeBasedRepository() {
        this.set = HashObjSets.newMutableSet();
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
