package by.it.academy.services.user;

import by.it.academy.entities.user.User;

import by.it.academy.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@Scope("prototype")
@RequiredArgsConstructor
public class UserDBService implements UsersService<User> {
    private final UserRepository userRepository;



    /**
     * this method creates a new user
     * @param user get from controller
     * @return save user
     */
    @Override
    public UUID create(User user) {
        final User buildUser = buildUser(user);
        return userRepository.save(buildUser).getId();

    }

    /**
     * this method counts the user in the database
     * @param user get from controller
     * @return user
     */
    @Override
    public Optional<User> read(User user) {
        UUID id = buildUser(user).getId();
        User users = userRepository.findById(id).get();
        Optional<User> optionalUser = Optional.ofNullable(user);
        return optionalUser;
    }

    /**
     * update user by id
     * @param user get from controller
     * @param id get from controller
     * @return false
     */
    @Override
    public boolean update(User user, UUID id ) {
        UUID idUser = buildUser(user).getId();
        User userRead = userRepository.findById(idUser).get();
        if (Objects.nonNull(userRead)){
            userRead.setLogin(user.getLogin());
            userRead.setUserType(user.getUserType());
            userRead.setPassword(user.getPassword());
            userRepository.save(userRead);

        }else {
            log.info("User not fount");
        }
        return false;
    }

    /**
     * deleted product by id
     * @param user get from controller
     * @return false
     */
    @Override
    public boolean delete (User user) {
        UUID id = buildUser(user).getId();
        userRepository.deleteById(id);
         return true;
    }

    /**
     * this method receives the set value from the
     * @return the received attribute value
     */
    @Override
    public List<User> readAllUser() {
        List<User> users = new ArrayList<>();
       userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * this method using build creates a new user
     * @return new user
     */
    private User buildUser(User user) {
        return User.builder()
                .id(user.getId())
                .userType(user.getUserType())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
