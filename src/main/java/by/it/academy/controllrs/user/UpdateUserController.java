package by.it.academy.controllrs.user;


import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/User/update")
@RequiredArgsConstructor
public class UpdateUserController {
    private final UsersService usersService;

    /**
     * this method updates user by id
     * @param user get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateUser(@RequestBody @Valid User user, UUID id){
        return usersService.update(user, id);
    }
}
