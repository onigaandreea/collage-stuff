package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class TaskDTO extends Entity<Integer> implements Serializable {
    private String description;
    private int ageCatStart;
    private int ageCatEnd;
    private int noChildren;

    public TaskDTO(String description, int ageCatStart, int ageCatEnd, int noChildren) {
        this.description = description;
        this.ageCatStart = ageCatStart;
        this.ageCatEnd = ageCatEnd;
        this.noChildren = noChildren;
    }

    public TaskDTO() {
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

    public int getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(int noChildren) {
        this.noChildren = noChildren;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "description='" + description + '\'' +
                ", ageCatStart=" + ageCatStart +
                ", ageCatEnd=" + ageCatEnd +
                ", noChildren=" + noChildren +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return ageCatStart == taskDTO.ageCatStart && ageCatEnd == taskDTO.ageCatEnd && noChildren == taskDTO.noChildren && Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, ageCatStart, ageCatEnd, noChildren);
    }
}
