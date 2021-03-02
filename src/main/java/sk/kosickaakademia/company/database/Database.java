package sk.kosickaakademia.company.database;

import sk.kosickaakademia.company.log.Log;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {


    Log log = new Log();
    public Connection getConnection(){
        try {
            Properties props = new Properties();
            InputStream loader = getClass().getClassLoader().getResourceAsStream("database.properties");
            props.load(loader);
            String url = props.getProperty("url");
            String username=props.getProperty("username");
            String password=props.getProperty("password");
            Connection con = DriverManager.getConnection(url, username, password);
            log.print("Connection : success!");
            return con;
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        return null;
    }
    public void closeConnection(Connection con)  {

        if(con!=null) {
            try {
                con.close();
                log.print("Connection closed!");
            }catch(SQLException e){
                 log.error(e.toString());
            }
        }
    }
}
