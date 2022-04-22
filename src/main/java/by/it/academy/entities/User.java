package by.it.academy.entities;

import java.util.Map;
import java.util.Objects;

public class User {
    private Map<String, String> credential;
    private String login;
    private String password;
    private String userType;

    public User(String login, String password, String userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Map<String, String> getCredential() {
        return credential;
    }

    public void setCredential(Map<String, String> credential) {
        this.credential = credential;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(credential, user.credential) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(userType, user.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credential, login, password, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "credential=" + credential +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}