package by.it.academy.controllers.user;

import by.it.academy.entities.user.User;
import by.it.academy.services.user.UsersService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    public final UsersService usersService;

    /**
     * checks if the user exists in the database
     * @param user get from server
     * @return the user if there is one and returns the type of the user
     */
    @RequestMapping("/user/getUser")
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User getUser(@RequestBody @Valid User user) {
        return usersService.getUser(user);
    }

    /**
     * this method returns a collection of all user in the database
     * @return collection of all users
     */
    @RequestMapping("/user/readUsers")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readAllUser(@PageableDefault(page = 0)
                                      @SortDefault(sort = "name") Pageable pageable){
        return usersService.readUsers(pageable);
    }

    /**
     * this method creates a new user
     * @param user get from server
     * @return the UUID id of the created user
     */
    @RequestMapping("/insert")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE) //consumes по умолчанию потребляет, produces отдает
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createUser(@RequestBody @Valid User user){
        return usersService.createUser(user);
    }

    /**
     * this method removes the user from the database
     * @param user get from server
     * @return the  boolean of the deleted user
     */
    @RequestMapping("/delete")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean deleteUser(@RequestBody @Valid User user){
        return usersService.deleteUser(user);
    }

    /**
     * this method updates user by id
     * @param user get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */
    @RequestMapping("/update")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateUser(@RequestBody @Valid User user, UUID id){
        return usersService.updateUser(user, id);
    }

    @PostMapping("{userId}/avatar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID saveAvatar(@PathVariable UUID userId, @RequestBody @NonNull MultipartFile image){
       return usersService.saveAvatar(userId, image);
    }

    @GetMapping(value = "{userId}/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar(@PathVariable UUID userId){
        return usersService.getAvatar(userId);
    }


    @RequestMapping(name = "user/checkLogin",method = RequestMethod.HEAD)
    public void checkLoginExist(@RequestParam String login){
        usersService.checkLogin(login);
    }

    @RequestMapping(method = RequestMethod.GET)
    public void checkEmailExist(@RequestParam String email){
        usersService.existsUserEmail(email);
    }

}
