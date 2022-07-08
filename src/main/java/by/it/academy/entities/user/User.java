package by.it.academy.entities.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private UUID id;
    @Column(name = "Login")
    private String login;
    @Column(name = "Password")
    private String password;
    private String firstName;
    private String lastName;
    private String email;
   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JoinTable(name = "userType", joinColumns = @JoinColumn(name = "userId"),
   inverseJoinColumns = @JoinColumn(name = "typeId"))
    private Set<UserType> userType;

}