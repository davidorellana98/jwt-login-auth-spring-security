package com.davidorellana.jwtloginauthspringsecurity.user.service;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.model.UserDto;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    User findUserById(String id);
    User createUser(UserDto userDto);
    User updateUserById(String id, UserDto userDto);
    Boolean deleteUserById(String id);
    void deleteAllUsers();
    User findUserByEmail(String email);

}
