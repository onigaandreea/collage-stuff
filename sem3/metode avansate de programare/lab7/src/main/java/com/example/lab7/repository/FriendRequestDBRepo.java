package com.example.lab7.repository;

import com.example.lab7.domain.FriendRequest;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FriendRequestDBRepo implements Repository<Long, FriendRequest> {
    private final String url;
    private final String username;
    private final String password;

    public FriendRequestDBRepo(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public FriendRequest findOne(Long id) {
        for (FriendRequest fr : this.findAll()) {
            if(Objects.equals(fr.getId(), id)) {
                return fr;
            }
        }
        return null;
    }

    @Override
    public Iterable<FriendRequest> findAll() {
        Set<FriendRequest> friendshipRequests = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from requests");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = (long)resultSet.getInt("id");
                Long id_user = (long)resultSet.getInt("id_user1");
                Long id_requestedFriend =(long) resultSet.getInt("id_user2");
                String dateRequest = resultSet.getString("send_at");
                LocalDateTime date_parsed = LocalDateTime.parse(dateRequest);
                String status = resultSet.getString("status");

                FriendRequest friendship_request = new FriendRequest(id_user,id_requestedFriend,date_parsed);
                friendship_request.setId(id);
                friendship_request.setStatus(status);
                friendshipRequests.add(friendship_request);
            }
            return friendshipRequests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendshipRequests;

    }

    @Override
    public FriendRequest save(FriendRequest entity) {

        String sql = "insert into requests (id_user1, id_user2, send_at, status) values (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (int)entity.getIdUser1());
            ps.setInt(2, (int)entity.getIdUser2());
            ps.setString(3, entity.getSendRequest().toString());
            ps.setString(4, entity.getStatus());


            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FriendRequest remove(Long id) {
        String sql = "delete from requests where id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,id.intValue());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public FriendRequest update(FriendRequest entity) {
        String sql = "update requests set id_user1 = ?, id_user2 = ?, send_at = ?, status = ? where id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (int)entity.getIdUser1());
            ps.setInt(2, (int)entity.getIdUser2());
            ps.setString(3, entity.getSendRequest().toString());
            ps.setString(4, entity.getStatus());
            ps.setInt(5, entity.getId().intValue());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long size() {
        int s = 0;
        for (FriendRequest fr : findAll()) {
            s++;
        }
        return s;
    }
}