package repository;

import domain.Friendship;
import domain.User;
import domain.validators.ValidationException;
import domain.validators.Validator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FriendshipDBRepo implements Repository<Long, Friendship> {
    private String url;
    private String username;
    private String password;
    private Validator<Friendship> validator;

    public FriendshipDBRepo(String url, String username, String password, Validator<Friendship> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }
    @Override
    public Friendship findOne(Long aLong) throws SQLException {
        for (Friendship friends: this.findAll()) {
            if(friends.getId() == aLong)
                return friends;
        }
        return null;
    }

    @Override
    public Iterable<Friendship> findAll() throws SQLException {
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
    public long size() throws SQLException {
        long size = 0;
        for(Friendship friendship: this.findAll())
            size++;
        return size;
    }

    @Override
    public Friendship save(Friendship entity) throws ValidationException {
        String sql = "insert into friends (id_user1, id_user2, friends_from) values (?, ?, ?)";
        validator.validate(entity);
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
    public Friendship remove(Long aLong) {
        String sql = "delete from friends where id = ?";

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
    public Friendship update(Friendship entity) throws ValidationException {
        return null;
    }

}
