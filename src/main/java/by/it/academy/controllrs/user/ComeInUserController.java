package by.it.academy.controllrs.user;

import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user/read")
@RequiredArgsConstructor
public class ComeInUserController {
    public final UsersService usersService;

    /**
     * checks if the user exists in the database
     * @param user get from server
     * @return the user if there is one and returns the type of the user
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String readUser(@RequestBody @Valid User user){
        Optional<User> read = usersService.read(user);
        String users = read.toString();

        if (Objects.nonNull(read)){
            return users;
        }else {
            log.info("User not fount");
        }

        String userType = read.stream()
                .map(User::getUserType)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .toString();
        return userType;
    }
}


