package by.it.academy.services.user;


import by.it.academy.entities.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersService{
    UUID createUser (User user);
    User getUser (User user);
    boolean updateUser (User user, UUID id);
    boolean deleteUser (User user);
    List<User> readUsers(Pageable pageable);

    void checkLogin(String login);
    void existsUserEmail(String email);

    UUID saveAvatar(UUID id, MultipartFile image);
    byte[] getAvatar(UUID id);

}
