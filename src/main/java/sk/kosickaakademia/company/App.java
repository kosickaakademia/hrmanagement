package sk.kosickaakademia.company;

import sk.kosickaakademia.company.database.Database;
import sk.kosickaakademia.company.entity.User;
import sk.kosickaakademia.company.util.Util;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Database db=new Database();
        //db.insertNewUser(new User("    MARiannA    ","    kováčiková       ",22,1));

        List<User> list = db.getAllUsers();
        String text = new Util().getJson(list);
        System.out.println(text);
    }
}
