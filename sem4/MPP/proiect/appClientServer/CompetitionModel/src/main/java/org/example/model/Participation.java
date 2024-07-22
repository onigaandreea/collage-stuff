package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Participation extends Entity<Integer> implements Serializable {
    private Child child;
    private Task task;

    public Participation(Child child, Task task) {
        this.child = child;
        this.task = task;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child childId) {
        this.child = child;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "child=" + child +
                ", task=" + task +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participation that = (Participation) o;
        return child == that.child && task == that.task;
    }

    @Override
    public int hashCode() {
        return Objects.hash(child, task);
    }
}
