package by.it.academy.repositories.user;

import by.it.academy.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select count(login) from users where login =: login", nativeQuery = true)
    int existActiveLogin(String login);


    @Query(value = "select count(email) from users where email =: email", nativeQuery = true)
    int existActiveEmail(String email);




}
