package by.it.academy.services.user;

import by.it.academy.entities.User;

import java.util.List;

public interface UserService <T>{
    void create(T user);
    User read (User user);
    boolean update (User user, User newUser);
    boolean delete (User user);
    List<User> readAllUser();

}
