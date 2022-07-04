package by.it.academy.controllrs.user;


import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.POST;
import java.util.UUID;


@Slf4j //логер
@RestController // рес подход когда нету вью нету страниц
@RequestMapping("/user")
@RequiredArgsConstructor // конструктор на все final поля
public class CreateUserController {
    private final UsersService usersService;

    /**
     * this method creates a new user
     * @param user get from server
     * @return the UUID id of the created user
     */
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE) //consumes по умолчанию потребляет, produces отдает
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@RequestBody @Valid User user){
        return usersService.create(user);
    }
}
