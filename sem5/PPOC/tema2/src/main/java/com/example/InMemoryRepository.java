package com.example;

public interface InMemoryRepository <T>{
    void add(T elem);
    boolean contains(T elem);
    void remove(T elem);
}
