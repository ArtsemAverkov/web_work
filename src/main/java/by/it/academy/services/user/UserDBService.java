package by.it.academy.services.user;

import by.it.academy.entities.User;
import by.it.academy.repositories.user.UsersRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserDBService implements UsersService<User> {
    private UsersRepository<User> repository;

    public UserDBService(UsersRepository<User> repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
     repository.createUser(user);
    }

    @Override
    public List<User> read(User user) {
        return repository.readUser(user)
                .stream()
                .flatMap(users -> users.stream())
                .collect(Collectors.toList());
    }

    @Override
    public boolean update(User user, User newUser) {
        return repository.updateUser(user, newUser);
    }

    @Override
    public boolean delete(User user) {
        return repository.deleteUser(user);
    }

    @Override
    public  Optional<List<User>> readAllUser() {
        return repository.readAllUser();

    }
}
