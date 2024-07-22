package org.example.dto;

import org.example.model.Task;

import java.io.Serializable;

public class TaskDTO extends Task implements Serializable {
    private int nrChildren;
    public TaskDTO(String description, int minAgeCat, int maxAgeCat, int number) {
        super(description, minAgeCat, maxAgeCat);
        this.nrChildren = number;
    }

    public int getNrChildren() {
        return nrChildren;
    }

    public void setNrChildren(int number) {
        this.nrChildren = number;
    }
}
