package by.it.academy.repositories.user;

import by.it.academy.entities.User;

import java.util.List;

public interface UserRepository <T> {

    void create(User user);
    User read(User user);
    boolean delete (User user);
    boolean update(User user, User newUser);
    List<User> readAllUser();
    User userType(User user);

}







