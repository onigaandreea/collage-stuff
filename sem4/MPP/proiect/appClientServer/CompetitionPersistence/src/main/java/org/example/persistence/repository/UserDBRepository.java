package org.example.persistence.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.User;
import org.example.persistence.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDBRepository implements IUserRepository {

    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public UserDBRepository(Properties props) {
        logger.info("Initializing UserDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public User findByEmail(String email) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from user where username = ?")){
            preStmt.setString(1, email);
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_user = result.getInt("user_id");
                    String name = result.getString("name");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    int office_no = result.getInt("office_no");
                    User user = new User(name,username,password,office_no);
                    user.setId(id_user);
                    return user;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public void add(User elem) {
        logger.traceEntry("saving task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into user(name, username, password, office_no)" +
                " values (?, ?, ?, ?)")){
            preStmt.setString(1, elem.getName());
            preStmt.setString(2, elem.getUsername());
            preStmt.setString(3, elem.getPassword());
            preStmt.setInt(4,elem.getOfficeNo());
            int result= preStmt.executeUpdate();
            logger.trace("Saved {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(User elem) {

    }

    @Override
    public void update(User elem, Integer id) {

    }

    @Override
    public User findById(Integer id) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from user where user_id = ?")){
            preStmt.setInt(1, id);
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_user = result.getInt("user_id");
                    String name = result.getString("name");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    int office_no = result.getInt("office_no");
                    User user = new User(name,username,password,office_no);
                    user.setId(id_user);
                    return user;
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<User> users=new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from user")){
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id = result.getInt("user_id");
                    String name = result.getString("name");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    int office_no = result.getInt("office_no");
                    User user = new User(name,username,password,office_no);
                    user.setId(id);
                    users.add(user);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(users);
        return users;
    }
}
