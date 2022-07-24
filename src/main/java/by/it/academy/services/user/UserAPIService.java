package by.it.academy.services.user;

import by.it.academy.entities.Avatar;
import by.it.academy.entities.user.User;

import by.it.academy.repositories.avatar.AvatarRepository;
import by.it.academy.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Scope("prototype")
@RequiredArgsConstructor
public class UserAPIService implements UsersService {
    private final UserRepository userRepository;
    private final AvatarRepository avatarRepository;



    /**
     * this method creates a new user
     * @param user get from controller
     * @return save user
     */
    @Override
    @Transactional
    public Long createUser (User user) {
        final User buildUser = buildUser(user);
        return userRepository.save(buildUser).getId();

    }

    /**
     * this method counts the user in the database
     * @param id get from controller
     * @return user
     */
    @Override

    public User getUser (Long id) {
        return  userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * update user by id
     * @param user get from controller
     * @param id get from controller
     * @return false
     */
    @Override
    @Transactional
    public boolean updateUser (User user, Long id ) {
        User users = getUser(id);
        if (Objects.nonNull(users)){
            throw  new NoSuchElementException();
        }else {
            User buildUser = buildUser(user);
            buildUser.setId(id);
            userRepository.save(buildUser);
        }
        return false;
    }

    /**
     * deleted product by id
     * @param id get from controller
     * @return false
     */
    @Override
    @Transactional
    public void deleteUser (Long id) {
        userRepository.deleteById(id);

    }

    /**
     * this method receives the set value from the
     * @return the received attribute value
     */
    @Override
    @Transactional
    public List<User> readUsers(Pageable pageable) {
        return  userRepository.findAll(pageable).toList();
    }

    /**
     *this method login uniqueness check
     * @param login get from controller
     */
    @Override
    public void checkLogin(String login) {
        int usersLogin = userRepository.existActiveLogin(login);
        if(Objects.nonNull(usersLogin)){
            log.warn("login is exists");
        }else{
            log.info("login does not exist");
        }
    }

    /**
     *this method email uniqueness check
     * @param email get from controller
     * @return 0
     */
    @Override
    @Transactional
    public int existsUserEmail(String email) {
      int existsUserEmail = userRepository.existActiveEmail(email);
        if (Objects.nonNull(existsUserEmail)){
            log.warn("email is exists");
        }else{
            log.info("email does not exists");
        }
        return 0;
    }

    /**
     * this method save avatar for user by id
     * @param id get from controller
     * @return id user
     */
    @SneakyThrows// в аннотацию прячет все exception
    @Override
    @Transactional
    public UUID saveAvatar(UUID id, MultipartFile image) {
        return avatarRepository.save(new Avatar(id, image.getBytes())).getId();
    }

    /**
     * this method get avatar for user by id
     * @param id get from controller
     * @return get image
     */
    @Override
    @Transactional
    public byte[] getAvatar(UUID id) {
        return avatarRepository.findById(id).orElseThrow(NoSuchElementException::new).getImage();
    }

    /**
     * this method using build creates a new user
     * @return new user
     */
    private User buildUser(User user) {
        return User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .userType(user.getUserType())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
