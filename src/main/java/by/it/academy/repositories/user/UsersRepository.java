package by.it.academy.repositories.user;

import by.it.academy.entities.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository <T>{
    void createUser(User user);
    Optional<List<User>>  readUser(User user);
    boolean deleteUser (User user);
    boolean updateUser(User user, User newUser);
    Optional<List<User>> readAllUser();


}
