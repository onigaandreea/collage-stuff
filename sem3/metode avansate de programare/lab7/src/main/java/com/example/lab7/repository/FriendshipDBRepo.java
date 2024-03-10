package com.example.lab7.repository;

import com.example.lab7.domain.Friendship;
import com.example.lab7.domain.validator.FriendshipValidator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FriendshipDBRepo implements Repository<Long, Friendship> {
    private final String url;
    private final String username;
    private final String password;
    private final FriendshipValidator validator;

    public FriendshipDBRepo(String url, String username, String password, FriendshipValidator validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Friendship findOne(Long id) {
        for (Friendship fr : this.findAll()) {
            if(fr.getId() == id) {
                return fr;
            }
        }
        return null;
    }

    @Override
    public Iterable<Friendship> findAll() {
        Set<Friendship> friendships = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from friends");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                Long idUser1 = (long)resultSet.getInt("id_user1");
                Long idUser2 = (long)resultSet.getInt("id_user2");
                String from = resultSet.getString("friends_from");
                LocalDateTime from_parsed = LocalDateTime.parse(from);


                Friendship friends = new Friendship(idUser1,idUser2,from_parsed);
                friends.setId(id);
                friendships.add(friends);
            }
            return friendships;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendships;
    }

    @Override
    public Friendship save(Friendship entity) {

        String sql = "insert into friends (id_user1, id_user2, friends_from) values (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, ((int) entity.getIdUser1()));
            ps.setInt(2, ((int) entity.getIdUser2()));
            ps.setString(3, entity.getFriendsFrom().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Friendship remove(Long id) {
        String sql = "delete from friends where id = ?";

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
    public Friendship update(Friendship entity) {
        String sql = "update friendships set id_user1 = ?, id_user2 = ?, friends_form = ? where id = ?";


        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, (int)entity.getIdUser1());
            ps.setInt(2, (int)entity.getIdUser2());
            ps.setString(3, entity.getFriendsFrom().toString());
            ps.setInt(4, entity.getId().intValue());

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
        for (Friendship fr : findAll()) {
            s++;
        }
        return s;
    }
}
