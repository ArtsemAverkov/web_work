package by.it.academy.services.user;


import by.it.academy.entities.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface UsersService{
    Long createUser (User user);
    User getUser (Long id);
    boolean updateUser (User user, Long id);
    void deleteUser (Long id);
    List<User> readUsers(Pageable pageable);

    void checkLogin(String login);
    int existsUserEmail(String email);

    UUID saveAvatar(UUID id, MultipartFile image);
    byte[] getAvatar(UUID id);

}
