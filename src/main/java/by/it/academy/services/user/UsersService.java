package by.it.academy.services.user;


import by.it.academy.entities.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService <User>{
    UUID create(User user);
    Optional<User> read (User user);
    boolean update (User user, UUID id);
    boolean delete (User user);
    List<User> readAllUser();

}
