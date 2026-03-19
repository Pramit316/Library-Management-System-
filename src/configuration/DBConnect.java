package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    Connection con;

    String url = "jdbc:postgresql://localhost:5432/library_management_jdbc";
    String user = "postgres";
    String password = "12345";

    public Connection getConnection(){

        try{
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Failed to connet to database: Error \n" + e);
        }

        return con;
    }
}
