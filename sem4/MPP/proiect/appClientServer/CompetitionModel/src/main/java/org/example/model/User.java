package org.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@javax.persistence.Entity
@Table(name= "user")
public class User extends Entity<Integer> implements Serializable {
    private String name;
    private String username;
    private String password;
    private int officeNo;
    public User() { }

    public User(String name, String username, String password, int officeNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.officeNo = officeNo;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", updatable = false, nullable = false)
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "office_no")
    public int getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(int officeNo) {
        this.officeNo = officeNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", officeNo=" + officeNo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return officeNo == user.officeNo && name.equals(user.name) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, username, password, officeNo);
    }
}
