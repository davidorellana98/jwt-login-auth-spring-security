package com.davidorellana.jwtloginauthspringsecurity.user.service;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.model.UserDto;
import com.davidorellana.jwtloginauthspringsecurity.user.repository.UserRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryDao userRepositoryDao;

    @Autowired
    public UserServiceImpl(UserRepositoryDao userRepositoryDao) {
        this.userRepositoryDao = userRepositoryDao;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryDao.findAllUsers();
    }

    @Override
    public User findUserById(String id) {
        return userRepositoryDao.findUserById(id);
    }

    @Override
    public User createUser(UserDto userDto) {
        return userRepositoryDao.createUser(userDto);
    }

    @Override
    public User updateUserById(String id, UserDto userDto) {
        return userRepositoryDao.updateUserById(id, userDto);
    }

    @Override
    public Boolean deleteUserById(String id) {
        return userRepositoryDao.deleteUserById(id);
    }

    @Override
    public void deleteAllUsers() {
        userRepositoryDao.deleteAllUsers();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepositoryDao.findUserByEmail(email);
    }
}
