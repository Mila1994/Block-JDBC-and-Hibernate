package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE users (\n" +
                "    id BIGSERIAL PRIMARY KEY,\n" +
                "    name VARCHAR(100),\n" +
                "    lastName VARCHAR(100),\n" +
                "    age INT \n" +
                ");";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public void dropUsersTable() {
        String sql = "drop table users";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSQL = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(insertSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeSQL = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = conn.prepareStatement(removeSQL);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String selectSQL = "SELECT id, name, lastName, age FROM users";
        List<User> users = new ArrayList<User>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSQL);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                Byte age = resultSet.getByte("age");

                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ;
        }
    }
}
