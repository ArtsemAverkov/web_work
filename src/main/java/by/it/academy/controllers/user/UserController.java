package by.it.academy.controllers.user;

import by.it.academy.dtos.request.bucket.BucketDto;
import by.it.academy.entities.user.User;
import by.it.academy.services.bucket.BucketService;
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
    public  final BucketService bucketService;

    /**
     * checks if the user exists in the database
     * @param id get from server
     * @return the user if there is one and returns the type of the user
     */

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Long id) {
        return usersService.getUser(id);
    }

    /**
     * this method returns a collection of all user in the database
     * @return collection of all users
     */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> readAllUser(   @PageableDefault(page = 0)
                                      @SortDefault(sort = "firstName") Pageable pageable){
        return usersService.readUsers(pageable);
    }

    /**
     * this method creates a new user
     * @param user get from server
     * @return the UUID id of the created user
     */

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody @Valid User user){
        return usersService.createUser(user);
    }

    /**
     * this method removes the user from the database
     * @param id get from server
     */

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
       usersService.deleteUser(id);
    }

    /**
     * this method updates user by id
     * @param user get from server
     * @param id get from server
     * @returт successful and unsuccessful update
     */

    @PatchMapping (consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateUser(@PathVariable Long id, @RequestBody @Valid User user){
        return usersService.updateUser(user, id);
    }

    /**
     * this method save Avatar for user by id
     * @param userId get from server
     * @param image get from local
     * @returт successful and unsuccessful save
     */

    @PostMapping("{userId}/avatar")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UUID saveAvatar(@PathVariable UUID userId, @RequestBody @NonNull MultipartFile image){
       return usersService.saveAvatar(userId, image);
    }

    /**
     * this method get Avatar for user by id
     * @param userId get from server
     * @returт successful and unsuccessful get Avatar
     */

    @GetMapping(value = "{userId}/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getAvatar(@PathVariable UUID userId){
        return usersService.getAvatar(userId);
    }


    /**
     * this method login uniqueness check
     * @param login get from server
     */

    @RequestMapping(name = "user/checkLogin",method = RequestMethod.HEAD)
    public void checkLoginExist(@RequestParam String login){
        usersService.checkLogin(login);
    }

    /**
     * this method email uniqueness check
     * @param email get from server
     */

    @RequestMapping(name = "user/checkEmail", method = RequestMethod.HEAD)
    public void checkEmailExist(@RequestParam String email){
        usersService.existsUserEmail(email);
    }

    /**
     * this method get product bucket
     * @param id get from server
     * @returт successful and unsuccessful get bucket
     */

    @GetMapping(path = "bucket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BucketDto getProductBucket(@PathVariable Long id){
        return  bucketService.getBucket(id);
    }

    /**
     * this method add product bucket
     * @param bucketDto get from dataBase
     * @returт successful and unsuccessful bucket
     */

    @PutMapping(path = "bucket")
    @ResponseStatus(HttpStatus.OK)
    public Long addProductBucket(@RequestBody @Valid BucketDto bucketDto){
        return  bucketService.createBucket(bucketDto);
    }

    /**
     * this method delete product bucket
     * @param id get from server
     * @returт successful and unsuccessful delete product bucket
     */

    @DeleteMapping(path = "bucket/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductBucket(@PathVariable Long id){
        bucketService.deleteBucket(id);
    }



}
