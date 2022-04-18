package by.it.academy.repositories.user;

import by.it.academy.entities.Product;
import by.it.academy.entities.User;
import by.it.academy.repositories.connect.Connect;
import by.it.academy.repositories.connect.ConnectMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserApiRepository implements UserRepository<User> {
    Connect connection;


    public UserApiRepository(List<User> users) {
        connection = new ConnectMySQL();
    }

    @Override
    public void create(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO user1 (login, password) VALUES (?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            int rowsInserted = statement.executeUpdate();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User read(User user) {
        User newUser = null;
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(user.getLogin()) && resultSet.getString("password").equals(user.getPassword())) {
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    newUser = new User(login, password);
                    connection.close();
                    return newUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    @Override
    public boolean delete(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM user1 WHERE login=? AND password=?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user, User newUser) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("UPDATE user1 SET login=?, password=? WHERE login=? AND password=?");
            statement.setString(1, newUser.getLogin());
            statement.setString(2, newUser.getPassword());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());

            int i = statement.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> readAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM user1");
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(login, password));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }
}

