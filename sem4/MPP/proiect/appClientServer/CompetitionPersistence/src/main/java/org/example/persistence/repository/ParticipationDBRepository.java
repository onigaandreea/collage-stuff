package org.example.persistence.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Child;
import org.example.model.Participation;
import org.example.model.Task;
import org.example.persistence.IChildRepository;
import org.example.persistence.IParticipationRepository;
import org.example.persistence.ITaskRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ParticipationDBRepository implements IParticipationRepository {
    private JdbcUtils dbUtils;
    private IChildRepository ChildRepo;
    private ITaskRepository TaskRepo;

    private static final Logger logger= LogManager.getLogger();

    public ParticipationDBRepository(Properties props, IChildRepository child, ITaskRepository task) {
        logger.info("Initializing ParticipationDBRepository with properties: {} ",props);
        dbUtils=new JdbcUtils(props);
        ChildRepo = child;
        TaskRepo = task;
    }
    @Override
    public Iterable<Participation> findByTask(Task task) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Participation> participations = new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from participation where id_task = ?")){
            preStmt.setInt(1, task.getId());
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int idP = result.getInt("id_participation");
                    int idC = result.getInt("id_child");
                    int idT = result.getInt("id_task");
                    Child child = ChildRepo.findById(idC);
                    Task task1 = TaskRepo.findById(idT);
                    Participation participation = new Participation(child,task);
                    participation.setId(idP);
                    participations.add(participation);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(participations);
        return participations;
    }

    @Override
    public void add(Participation elem) {
        logger.traceEntry("saving task {} ", elem);
        Connection con= dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into participation (id_child, id_task) values (?, ?)")){
            preStmt.setInt(1, elem.getChild().getId());
            preStmt.setInt(2, elem.getTask().getId());
            int result= preStmt.executeUpdate();
            logger.trace("Saved {} instances", elem);
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit();
    }

    @Override
    public void delete(Participation elem) {

    }

    @Override
    public void update(Participation elem, Integer id) {

    }

    @Override
    public Participation findById(Integer id) {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        try (PreparedStatement preStmt=con.prepareStatement("select * from participation where id_participation = ?")){
            preStmt.setInt(1, id);
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int idP = result.getInt("id_participation");
                    int idC = result.getInt("id_child");
                    int idT = result.getInt("id_task");
                    Child child = ChildRepo.findById(idC);
                    Task task = TaskRepo.findById(idT);
                    Participation participation = new Participation(child,task);
                    participation.setId(idP);
                    return participation;
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
    public Iterable<Participation> findAll() {
        logger.traceEntry();
        Connection con= dbUtils.getConnection();
        List<Participation> participations = new ArrayList<>();
        try (PreparedStatement preStmt=con.prepareStatement("select * from participation")){
            try (ResultSet result= preStmt.executeQuery()){
                while (result.next()){
                    int idP = result.getInt("id_participation");
                    int idC = result.getInt("id_child");
                    int idT = result.getInt("id_task");
                    Child child = ChildRepo.findById(idC);
                    Task task = TaskRepo.findById(idT);
                    Participation participation = new Participation(child,task);
                    participation.setId(idP);
                    participations.add(participation);
                }
            }
        }catch (SQLException ex){
            logger.error(ex);
            System.err.println("Error DB " + ex);
        }
        logger.traceExit(participations);
        return participations;
    }
}
