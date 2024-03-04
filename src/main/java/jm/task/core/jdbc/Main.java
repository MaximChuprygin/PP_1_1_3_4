package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService1 = new UserServiceImpl();

        try {
            userService1.dropUsersTable();

            userService1.createUsersTable();

            userService1.saveUser("1","1", (byte) 1);
            userService1.saveUser("2","2", (byte) 2);
            userService1.saveUser("3","3", (byte) 3);
            userService1.saveUser("4","4", (byte) 4);

            List<User> users = userService1.getAllUsers();
            for (User a : users ) {
                System.out.println(a);
            }

            userService1.removeUserById(19);

            List <User> users1 = userService1.getAllUsers();
            for (User a : users1 ) {
                System.out.println(a);
            }

            userService1.cleanUsersTable();

            List <User> users2 = userService1.getAllUsers();
            for (User a : users2 ) {
                System.out.println(a);
            }

            userService1.dropUsersTable();

        } catch (Exception e) {

        }
        System.out.println("конец");
    }
}
