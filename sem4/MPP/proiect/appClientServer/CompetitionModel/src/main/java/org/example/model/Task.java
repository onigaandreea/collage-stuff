package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class Task extends Entity<Integer> implements Serializable {
    @JsonProperty("description")
    private String description;
    @JsonProperty("agestart")
    private int ageCatStart;
    @JsonProperty("ageend")
    private int ageCatEnd;

    public Task(String description, int ageCatStart, int ageCatEnd) {
        this.description = description;
        this.ageCatStart = ageCatStart;
        this.ageCatEnd = ageCatEnd;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeCatStart() {
        return ageCatStart;
    }

    public void setAgeCatStart(int ageCatStart) {
        this.ageCatStart = ageCatStart;
    }

    public int getAgeCatEnd() {
        return ageCatEnd;
    }

    public void setAgeCatEnd(int ageCatEnd) {
        this.ageCatEnd = ageCatEnd;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", ageCatStart=" + ageCatStart +
                ", ageCatEnd=" + ageCatEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return ageCatStart == task.ageCatStart && ageCatEnd == task.ageCatEnd && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, ageCatStart, ageCatEnd);
    }
}
