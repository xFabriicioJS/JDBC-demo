package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;

public class DB {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try{
            Properties props = loadProperties();
            String url = props.getProperty("dburl");

            conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException error){
                throw new DbException(error.getMessage());
            }

        }

        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try{
                conn.close();

            }catch(SQLException error){
                throw new DbException(error.getMessage());
            }
        }
    }


    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;

        }
        catch (IOException error){
            throw new DbException(error.getMessage());
        }
    }

    public static void closeStatement(Statement statement){
        if( statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
               throw new DbException(e.getMessage());
            }
            
        }
    }

    public static void closeResultSet(ResultSet resultSet){
        if( resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
               throw new DbException(e.getMessage());
            }
            
        }
    }
}
