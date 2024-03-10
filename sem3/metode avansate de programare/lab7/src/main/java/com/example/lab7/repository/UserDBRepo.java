package com.example.lab7.repository;

import com.example.lab7.domain.User;
import com.example.lab7.domain.validator.ValidationException;
import com.example.lab7.domain.validator.Validator;

import java.sql.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDBRepo implements Repository<Long,User>{
    private final String url;
    private final String username;
    private final String password;
    private final Validator<User> validator;

    public UserDBRepo(String url, String username, String password, Validator<User> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }
    @Override
    public User findOne(Long aLong){
        for (User user: this.findAll()) {
            if(Objects.equals(user.getId(), aLong))
                return user;
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User utilizator = new User(firstName, lastName, email);
                utilizator.setId(id);
                utilizator.setPassword(password);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public long size() {
        long size = 0;
        for(User user: this.findAll())
            size++;
        return size;
    }

    @Override
    public User save(User entity) {
        String sql = "insert into users (first_name, last_name, email, password) values (?, ?, ?, ?)";

        try
        {
            this.validator.validate(entity);
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement ps = connection.prepareStatement(sql)) {

                ps.setString(1, entity.getFirstName());
                ps.setString(2, entity.getLastName());
                ps.setString(3, entity.getEmail());
                ps.setString(4, entity.getPassword());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public User remove(Long aLong) {
        String sql = "delete from users where id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,aLong.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User entity) {
        String sql = "update users set first_name = ?, last_name = ?, email = ?, password = ? where id = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setString(3, entity.getEmail());
            ps.setString(4, entity.getPassword());
            ps.setInt(5, entity.getId().intValue());


            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
