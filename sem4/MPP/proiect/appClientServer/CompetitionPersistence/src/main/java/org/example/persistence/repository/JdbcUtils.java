package org.example.persistence.repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {

    private Properties props;

    private static final Logger logger= LogManager.getLogger();

    public JdbcUtils(Properties props){
        this.props=props;
    }

    private static Connection instance=null;

    private Connection getNewConnection(){
        String driver=props.getProperty("jdbc.driver");
        String url=props.getProperty("jdbc.url");
        String user=props.getProperty("jdbc.user");
        String pass=props.getProperty("jdbc.pass");
        Connection con=null;
        try {
            //Class.forName(driver);
            con= DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            System.out.println("Error getting connection "+e);
        }
        return con;
    }

    public Connection getConnection(){
        try {
            if (instance==null || instance.isClosed())
                instance=getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB "+e);
        }
        return instance;
    }
}

