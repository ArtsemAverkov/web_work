package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserApiRepository;
import by.it.academy.repositories.user.UserRepository;

import java.util.List;

public class UserApiService implements UserService<User>{
     private UserRepository<User> repository;


    public UserApiService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
       repository.create(user);
    }

    @Override
    public User read(User user) {
        return  repository.read(user);
    }

    @Override
    public boolean update(User user, User newUser) {
        return repository.update(user, newUser);
    }

    @Override
    public boolean delete(User user) {
        return repository.delete(user);
    }

    @Override
    public List<User> readAllUser() {
        return repository.readAllUser();
    }


}
