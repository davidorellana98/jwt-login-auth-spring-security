package com.davidorellana.jwtloginauthspringsecurity.user.controller;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.model.UserDto;
import com.davidorellana.jwtloginauthspringsecurity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity("The user collection is empty.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) {
        User userByIdFound = userService.findUserById(id);
        if (userByIdFound != null) {
            return new ResponseEntity<>(userByIdFound, HttpStatus.OK);
        }
        return new ResponseEntity("The id " + id + " does not exist in the users collection.", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User userCreated = userService.createUser(userDto);
        if (userCreated != null) {
            return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
        }
        return new ResponseEntity("The creation of the user could not be carried out.", HttpStatus.CONFLICT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable String id, @RequestBody UserDto userDto) {
        User userUpdated = userService.updateUserById(id, userDto);
        if (userUpdated != null) {
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }
        return new ResponseEntity("User update failed.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable String id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity("Deleted user.", HttpStatus.OK);
        }
        return new ResponseEntity("The id " + id + " is not found in the collection of users to delete.", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity deleteAllUsers() {
        List<User> allUsers = userService.findAllUsers();
        userService.deleteAllUsers();
        if (allUsers.isEmpty()) {
            return new ResponseEntity("There is no collection of users to delete.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Correct deletion of the entire user collection.", HttpStatus.OK);
    }
}
