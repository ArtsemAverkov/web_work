package by.it.academy.controllrs.user;


import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/user/delete")
@RequiredArgsConstructor
public class DeleteUserController {
    private final UsersService usersService;

    /**
     * this method removes the user from the database
     * @param user get from server
     * @return the  boolean of the deleted user
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean deleteUser(@RequestBody @Valid User user){
        return usersService.delete(user);
    }
}
