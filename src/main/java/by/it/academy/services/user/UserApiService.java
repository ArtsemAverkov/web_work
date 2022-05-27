package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserApiService implements UserService<User> {
     private UserRepository<User> repository;


    public UserApiService(UserRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
       repository.create(user);
    }

    @Override
    public  List<User> read(User user) {
        return  repository.read(user)
                .stream()
                .collect(Collectors.toList());
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
        return repository.readAllUser()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public  List<User>  userType(User user) {
        return repository.userType(user)
                .stream()
                .collect(Collectors.toList());
    }


}
