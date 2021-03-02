package sk.kosickaakademia.company;

import sk.kosickaakademia.company.database.Database;

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
        db.getConnection();
    }
}
