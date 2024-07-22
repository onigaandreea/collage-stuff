package org.example.persistence.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Task;
import org.example.persistence.ITaskRepository;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class TaskDBRepository implements ITaskRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public TaskDBRepository(Properties props) {
        logger.info("Initializing TaskDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
    }
    @Override
    public void add(Task elem) {
        logger.traceEntry("saving task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into task (description, age_category_start, age_category_end) values (?, ?, ?)")){
            preStmt.setString(1, elem.getDescription());
            preStmt.setInt(2, elem.getAgeCatStart());
            preStmt.setInt(3, elem.getAgeCatEnd());
            int result= preStmt.executeUpdate();
            logger.trace("Saved {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Task elem) {
        logger.traceEntry("deleting task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from task where id_task=?")){

            preStmt.setInt(1, elem.getId());
            int result= preStmt.executeUpdate();
            logger.trace("Deleted {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void update(Task elem, Integer id) {
        logger.traceEntry("updating task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update task set description = ?, age_category_start = ?, age_category_end = ? where id_task=?")){
            preStmt.setString(1, elem.getDescription());
            preStmt.setInt(2, elem.getAgeCatStart());
            preStmt.setInt(3, elem.getAgeCatEnd());
            preStmt.setInt(4, id);
            int result= preStmt.executeUpdate();
            logger.trace("Updated {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public Task findById(Integer id) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from task where id_task = ?")){
            preStmt.setInt(1, id);
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_task = result.getInt("id_task");
                    String description = result.getString("description");
                    int ageStart = result.getInt("age_category_start");
                    int ageEnd = result.getInt("age_category_end");
                    Task task = new Task(description,ageStart,ageEnd);
                    task.setId(id_task);
                    return task;
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
    public Iterable<Task> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from task")){
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int id_task = result.getInt("id_task");
                    String description = result.getString("description");
                    int ageStart = result.getInt("age_category_start");
                    int ageEnd = result.getInt("age_category_end");
                    Task task = new Task(description,ageStart,ageEnd);
                    task.setId(id_task);
                    tasks.add(task);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(tasks);
        return tasks;
    }
}
