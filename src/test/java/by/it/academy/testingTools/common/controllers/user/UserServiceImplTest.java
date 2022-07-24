package by.it.academy.testingTools.common.controllers.user;


import by.it.academy.entities.user.User;
import by.it.academy.repositories.user.UserRepository;
import by.it.academy.services.user.UsersService;
import by.it.academy.testingTools.common.extensions.user.InvalidParameterResolver;
import by.it.academy.testingTools.common.extensions.user.ValidUserParameterResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;

@DisplayName("Testing User")
public class UserServiceImplTest {
    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(ValidUserParameterResolver.class)
    public class ValidData{
        @Mock
        private UserRepository userRepository;

        @InjectMocks
        private UsersService usersService;

        @DisplayName("All user are valid")
        @RepeatedTest(3)
        void shouldUpdateUserWhenUserIsValid(Long id, User user){
            Mockito.when(userRepository.findById(id)).thenReturn(Optional.ofNullable(user));
            Assertions.assertEquals(Objects.nonNull(user), usersService.updateUser(user, id));
            Mockito.verify(userRepository, Mockito.times(1)).save(user);
        }
        @RepeatedTest(3)
        void shouldCreateUserWhenUserIsValid(User user){
            Mockito.when(userRepository.save(user)).thenReturn(user);
            Assertions.assertEquals(user.getId(), usersService.createUser(user));
            Mockito.verify(userRepository, Mockito.times(1)).save(user);
        }

        @RepeatedTest(3)
        void shouldDeleteUserWhenUserIsValid(Long id){
            User user = new User();
            Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
            Mockito.verify(userRepository, Mockito.times(1)).deleteById(id);
        }

        @RepeatedTest(3)
        void shouldGetUserWhenUserIsValid(Long id){
            User user = new User();
            Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
            Assertions.assertEquals(user.getId(), usersService.getUser(id));
            Mockito.verify(userRepository, Mockito.times(1));
        }
    }


    @Nested
    @ExtendWith(MockitoExtension.class)
    @ExtendWith(InvalidParameterResolver.class)
    public class InvalidData{
        @InjectMocks
        private UsersService usersService;
        @RepeatedTest(3)
        void shouldUpdateUserWheUserIsInvalid (Long id, User user){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> usersService.updateUser(user, id));
        }
        @RepeatedTest(3)
        void shouldCreateUserWheUserIsInvalid (User user){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> usersService.createUser(user));
        }
        @RepeatedTest(3)
        void shouldDeleteUserWheUserIsInvalid (Long id){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> usersService.deleteUser(id));
        }
        @RepeatedTest(3)
        void shouldGetUserWheUserIsInvalid (Long id){
            Assertions.assertThrows(EntityNotFoundException.class,
                    () -> usersService.getUser(id));
        }
    }
}
