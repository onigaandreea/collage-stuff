package com.example.lab7.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    public static final long serialVersionUID = 8609175038441759607L;

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}