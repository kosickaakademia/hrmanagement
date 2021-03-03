package sk.kosickaakademia.company.database;

import sk.kosickaakademia.company.entity.User;
import sk.kosickaakademia.company.log.Log;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    Log log = new Log();
    private final String INSERTQUERY ="INSERT INTO user (fname, lname, age, gender) " +
            " VALUES ( ?, ?, ?, ?)";
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

    public boolean insertNewUser(User user){
        Connection con = getConnection();
        if(con!=null){
            try{
                PreparedStatement ps = con.prepareStatement(INSERTQUERY);
                ps.setString(1,user.getFname());
                ps.setString(2,user.getLname());
                ps.setInt(3,user.getAge());
                ps.setInt(4,user.getGender().getValue());
                int result = ps.executeUpdate();
                closeConnection(con);
                log.print("New user has been added to the DB");
                return result==1;
            }catch(SQLException ex){
                log.error(ex.toString());
            }
        }
        return false;
    }

}
