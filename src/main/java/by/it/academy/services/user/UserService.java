package by.it.academy.services.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService <T>{
    void create(T user);
    List<User> read (User user);
    boolean update (User user, User newUser);
    boolean delete (User user);
    List<User> readAllUser();
    List<User>  userType(User user);

}
