package by.it.academy.repositories.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository <T> {

    void create(User user);
    Optional<User> read(User user);
    boolean delete (User user);
    boolean update(User user, User newUser);
    List<Optional<User>> readAllUser();
    Optional<User> userType(User user);

}







