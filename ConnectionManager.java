package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
    private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";    
    private static String url = "jdbc:sqlserver://localhost;database=QLBH";   
    private static String username = "sa";   
    private static String password = "quoctu";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
                System.out.println("successfully to create the database connection."); 
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection."); 
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found."); 
        }
        return con;
    }
   
}
