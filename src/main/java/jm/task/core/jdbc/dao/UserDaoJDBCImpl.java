package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.getMyCon();
    Statement stmt = null;
    PreparedStatement stmtPrep = null;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user" +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                " name VARCHAR(255), " +
                " lastName VARCHAR(255), " +
                " age INTEGER)";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Sozdana tablica user");
        } catch (SQLException throwables) {
            System.out.println("Owibka sozdaniya tablici user");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Udalena tablica user");
        } catch (SQLException throwables) {
            System.out.println("Owibka udaleniya tablici user");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user " + "(name, lastname, age) VALUES (?, ?, ?)";
        try {
            stmtPrep = connection.prepareStatement(sql);
            stmtPrep.setString(1, name);
            stmtPrep.setString(2, lastName);
            stmtPrep.setByte(3, age);
            stmtPrep.executeUpdate();
            stmtPrep.close();
            System.out.println("dobavlen user" + name);
        } catch (SQLException throwables) {
            System.out.println("owibka dobavleniya user" + name);
        }

    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id=?";
        try {
            stmtPrep = connection.prepareStatement(sql);
            stmtPrep.setLong(1, id);
            stmtPrep.executeUpdate();
            stmtPrep.close();
            System.out.println("Udalen user");
        } catch (SQLException throwables) {
            System.out.println("Owibka udaleniya usera");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        String sql = "SELECT * FROM user";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);
                list.add(new User(name, lastName, age));
            }
            rs.close();
            stmt.close();
            System.out.println("Vivedeni vse users iz tablici user");
        } catch (SQLException throwables) {
            System.out.println("pri izvlechenii dannyh voznikla owibka");
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Tablica user ochiwena");
        } catch (SQLException throwables) {
            System.out.println("Pri ochiwenii tablici voznikla owibka");
        }


    }
}
