package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao  {
    public UserDaoJDBCImpl() {}
    private final Connection connection = Util.getConnection();

    public void createUsersTable()  {
        try {
            try ( Statement statement = connection.createStatement()) {

                statement.execute("CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (" +
                        "  `id` INT NOT NULL AUTO_INCREMENT," +
                        "  `name` VARCHAR(45) NOT NULL," +
                        "  `lastName` VARCHAR(45) NOT NULL," +
                        "  `age` INT NOT NULL," +
                        "  PRIMARY KEY (`id`),");
            }

        } catch (SQLException e ) {
            System.out.println("An error occurred while creating the table" + e.getMessage());
        }
        System.out.println("DB created successful by JDBC");
    }

    public void dropUsersTable()  {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.execute("DROP TABLE `mydbtest`.`users`;");
            }
        } catch (SQLException e) {
                System.out.println("An error occurred while drop the table" + e.getMessage());
            }
        System.out.println(" DB dropped successful by JDBC");

        }


    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO mydbtest.USERS (name,lastName,age) VALUES (?,?,?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while save user into table" + e.getMessage());
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id)  {
        String sql = "DELETE  FROM mydbtest.users WHERE id=?";

        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while removeUserById" + e.getMessage());
        }
        System.out.println("User с Id – " + id + " удален из БД");

    }
    public List<User> getAllUsers()  {

        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id,name,lastName,age FROM mydbtest.users";

        try {
            try (Statement statement2 = connection.createStatement()) {
                ResultSet resultSet = statement2.executeQuery(sql);

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));

                    usersList.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while getAllUsers" + e.getMessage());
        }
        return usersList;
    }

    public void cleanUsersTable()  {
        String sql = "DELETE  FROM mydbtest.users";

        try {
            try (PreparedStatement preparedStatement3 = connection.prepareStatement(sql)) {
                preparedStatement3.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while clean Users table" + e.getMessage());
        }
    }
}
