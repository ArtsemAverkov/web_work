package by.it.academy.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String login;
    private String password;
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
}