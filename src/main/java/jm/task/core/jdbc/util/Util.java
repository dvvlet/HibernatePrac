package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private final static String URL = "jdbc:mysql://localhost:3306/testjdbc?useSSL=false";
    private final static String USER = "root";
    private final static String PASSWORD = "7922050Sakuyuvf";

    public static Connection getMyCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ne udalos zagruzit driver");
            return null;
        }
    }
}
