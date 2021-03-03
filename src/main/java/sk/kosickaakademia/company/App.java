package sk.kosickaakademia.company;

import sk.kosickaakademia.company.database.Database;
import sk.kosickaakademia.company.entity.User;
import sk.kosickaakademia.company.enumerator.Gender;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Database db=new Database();

        db.insertNewUser(new User("Roman","Banik",35, 0));
        List<User> list = db.getMales();
        System.out.println(list);
    }
}
