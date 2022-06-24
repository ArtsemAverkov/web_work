package by.it.academy.entities;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Data
@Builder
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Login")
    private String login;
    @Column(name = "Password")
    private String password;
    @Column(name = "UserType", columnDefinition = "User")
    private String userType;

    public User() {
    }

    public User(String login, String password, String userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(Long id, String login, String password, String userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }
}