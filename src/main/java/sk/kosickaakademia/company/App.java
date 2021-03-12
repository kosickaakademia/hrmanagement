package sk.kosickaakademia.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sk.kosickaakademia.company.database.Database;
import sk.kosickaakademia.company.entity.User;
import sk.kosickaakademia.company.util.Util;

import java.util.List;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);

    }
}
