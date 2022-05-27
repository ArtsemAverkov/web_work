package by.it.academy.repositories.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.connect.Connect;
import by.it.academy.repositories.connect.ConnectMySQL;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserApiRepository implements UserRepository<User> {
    Connect connection;
    private final Logger logger = Logger.getLogger(UserApiRepository.class);
    public UserApiRepository(List<User> users) {
        connection = new ConnectMySQL();
    }

    @Override
    public void create(User user) {
        try (Connection conn = connection.connect()) {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO user1 (login, password) VALUES (?,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
            connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
    }

    @Override
    public  Optional<User> read(User user) {
        Optional<User> newUser = Optional.of(new User());
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1");
            while (resultSet.next()) {
                if (resultSet.getString("login").equals(user.getLogin()) && resultSet.getString("password").equals(user.getPassword())) {
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    newUser = Optional.ofNullable(User.builder()
                            .login(login)
                            .password(password)
                            .build());
                    connection.close();
                    return newUser;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
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

        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
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
            statement.executeUpdate();
            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            logger.info(ex);
        }
        return false;
    }

    @Override
    public List<Optional<User>> readAllUser() {
        List<Optional<User>> users = new ArrayList<>();
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT*FROM user1");
            while (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(Optional.ofNullable(User.builder()
                        .login(login)
                        .password(password)
                        .build()));
            }
        } catch (SQLException | ClassNotFoundException e ) {
            logger.info("UserApiRepository readAllUser" + e);
        }
        return users;
    }

    @Override
    public Optional<User> userType(User user) {
        Optional<User> newUser = Optional.of(new User());
        try (Connection conn = connection.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM user1");
            while (resultSet.next()) {
                // if (resultSet.getString("login").equals(user.getLogin()) && resultSet.getString("password").equals(user.getPassword())){
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String userType = resultSet.getString("userType");
                newUser = Optional.ofNullable(User.builder()
                        .login(login)
                        .password(password)
                        .userType(userType)
                        .build());
                logger.info("UserRepositoryReadUser :" + newUser);
                connection.close();
                return newUser;
           // }
            }
        } catch (SQLException | ClassNotFoundException e) {
            logger.info(e);
        }
        return newUser;
    }
}

