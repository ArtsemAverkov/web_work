package by.it.academy.repositories.user;

import by.it.academy.entities.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select count(login) from users where login =: login", nativeQuery = true)
    boolean existActiveLogin(String login);
    //boolean existsUsersLogin(String login);

    @Query(value = "select count(login) from users where email =: email", nativeQuery = true)
    boolean existActiveEmail(String email);
   // boolean existsUserEmail(String email);



}
