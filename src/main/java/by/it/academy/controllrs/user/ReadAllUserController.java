package by.it.academy.controllrs.user;


import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user/readAllUser")
@RequiredArgsConstructor
public class ReadAllUserController {
    private final UsersService usersService;

    /**
     * this method returns a collection of all user in the database
     * @return collection of all users
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> readAllUser(){
        return usersService.readAllUser();
    }
}
