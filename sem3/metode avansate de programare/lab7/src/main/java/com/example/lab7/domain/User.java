package com.example.lab7.domain;

import java.util.Objects;

public class User extends Entity<Long>{
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    //private boolean deleted = false;
    private boolean community = false;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User() {
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isInCommunity() {
        return community;
    }

    public void setCommunity(boolean community) {
        this.community = community;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return firstName.equals(user.firstName) && lastName.equals(user.lastName) &&
                email.equals(user.email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id= " + this.getId() +
                ",firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getFirstName(),this.getLastName(),this.getEmail());
    }
}