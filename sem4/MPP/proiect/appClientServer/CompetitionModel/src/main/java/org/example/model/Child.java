package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class Child extends Entity<Integer> implements Serializable {
    private String name;
    private int age;

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Child() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return age == child.age && name.equals(child.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
