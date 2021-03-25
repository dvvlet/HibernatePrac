package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        User daulet = new User("daulet", "zholdasbek", (byte)27);
        User sanzhar = new User("sanzhar", "zholdasbek", (byte)21);
        User ronaldo = new User("cristiano", "ronaldo", (byte) 33);
        User novak = new User("Novak", "djokovich", (byte) 35);
        userService.saveUser(daulet.getName(), daulet.getLastName(), daulet.getAge());
        userService.saveUser(sanzhar.getName(), sanzhar.getLastName(), sanzhar.getAge());
        userService.saveUser(ronaldo.getName(), ronaldo.getLastName(), ronaldo.getAge());
        userService.saveUser(novak.getName(), novak.getLastName(), novak.getAge());
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
