package by.it.academy.services.user;


import java.util.List;
import java.util.Optional;

public interface UsersService <User>{
    void create(User user);
    List<User> read (User user);
    boolean update (User user, User newUser);
    boolean delete (User user);
    Optional<List<User>> readAllUser();

}
