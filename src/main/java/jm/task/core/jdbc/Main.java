package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
//        User user1 = new User("Ivan", "Ivanov", (byte) 50);
//        User user2 = new User("Sergey", "Sidorov", (byte) 25);
//        User user3 = new User("Frenk", "Galager", (byte) 30);
//        User user4 = new User("Bruse", "Willis", (byte) 56);

        userService.saveUser("Ivan", "Ivanov", (byte) 50);
        System.out.println("Пользователь с именем – Ivan добавлен в базу данных");

        userService.saveUser("Sergey", "Sidorov", (byte) 25);
        System.out.println("Пользователь с именем – Sergey добавлен в базу данных");

        userService.saveUser("Frenk", "Galager", (byte) 30);
        System.out.println("Пользователь с именем – Frenk добавлен в базу данных");

        userService.saveUser("Bruce", "Willis", (byte) 56);
        System.out.println("Пользователь с именем – Bruce добавлен в базу данных");


        List<User> allUsers = userService.getAllUsers();
        System.out.println("Все пользователи " + allUsers);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
