package com.example.lab7.repository;

import com.example.lab7.domain.Message;
import com.example.lab7.domain.User;
import com.example.lab7.domain.validator.ValidationException;
import com.example.lab7.domain.validator.Validator;

import java.sql.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MessageDBRepo implements Repository<Long,Message> {
    private final String url;
    private final String username;
    private final String password;

    public MessageDBRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    @Override
    public Message findOne(Long aLong){
        for (Message message: this.findAll()) {
            if(Objects.equals(message.getId(), aLong))
                return message;
        }
        return null;
    }

    @Override
    public Iterable<Message> findAll() {
        Set<Message> messages = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from messages");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long message_from = resultSet.getLong("message_from");
                Long message_to = resultSet.getLong("message_to");
                String sent = resultSet.getString("sent");
                String message_text = resultSet.getString("message_text");

                LocalTime sent_parsed = LocalTime.parse(sent);

                Message message = new Message(message_from,message_to,sent_parsed,message_text);
                message.setId(id);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public long size() {
        long size = 0;
        for(Message m: this.findAll())
            size++;
        return size;
    }

    @Override
    public Message save(Message entity) {
        String sql = "insert into messages (message_from, message_to, sent, message_text) values (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getFrom());
            ps.setLong(2, entity.getTo());
            ps.setString(3, entity.getWhenWasSended().toString());
            ps.setString(4, entity.getText());

            ps.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Message remove(Long aLong) {
        return null;
    }

    @Override
    public Message update(Message entity) {
        return null;
    }
}
