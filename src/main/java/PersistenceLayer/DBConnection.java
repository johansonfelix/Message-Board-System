package PersistenceLayer;

import BusinessLayer.MessageBoardModel;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {


    // JDBC drivername and database URL
    static String JDBC_DRIVER;
    static  String DB_URL;
    static  String DB_NAME;
    // Database credentials
    static String DB_USER;
    static String DB_PASSWORD;


    static Connection conn = null;

    public static Connection getConnection() throws IOException {

        //initialise the data members with db.properties info
        initializeDBInfo();

        try{
            //Register JDBC driver
               Class.forName(JDBC_DRIVER);
            //Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL+DB_NAME,DB_USER,DB_PASSWORD);
            return conn;
        } catch (SQLException e){
            throw new RuntimeException("Error connecting to database",e);
        } catch (ClassNotFoundException e){
            throw new RuntimeException("Error Class Not Found",e);
        }
    }

    public static void closeConnection() throws SQLException{
        //Close connection
        if(conn!=null) conn.close();
    }
    private static void initializeDBInfo() throws IOException {
        Properties properties = new Properties();
       properties.load(MessageBoardModel.class.getResourceAsStream("../configurations/db.properties"));



        JDBC_DRIVER = properties.getProperty("jdbc.driver");
        DB_URL = properties.getProperty("jdbc.url");
        DB_NAME = properties.getProperty("jdbc.db_name");
        DB_USER = properties.getProperty("jdbc.username");
        DB_PASSWORD = properties.getProperty("jdbc.password");
    }


}
