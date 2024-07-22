package org.example.persistence.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Child;
import org.example.persistence.IChildRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ChildDBRepository implements IChildRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ChildDBRepository(Properties props) {
        logger.info("Initializing ChidDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public void add(Child elem) {
        logger.traceEntry("saving task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into child (child_name, child_age) values (?, ?)")){
            preStmt.setString(1, elem.getName());
            preStmt.setInt(2, elem.getAge());
            int result= preStmt.executeUpdate();
            logger.trace("Saved {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Child elem) {

    }

    @Override
    public void update(Child elem, Integer id) {

    }

    @Override
    public Child findById(Integer id) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from child where id_child = ?")){
            preStmt.setInt(1, id);
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_child = result.getInt("id_child");
                    String name = result.getString("child_name");
                    int age = result.getInt("child_age");
                    Child child = new Child(name,age);
                    child.setId(id_child);
                    return child;
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
    public Iterable<Child> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Child> children = new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from child")){
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_child = result.getInt("id_child");
                    String name = result.getString("child_name");
                    int age = result.getInt("child_age");
                    Child child = new Child(name,age);
                    child.setId(id_child);
                    children.add(child);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(children);
        return children;
    }
}
