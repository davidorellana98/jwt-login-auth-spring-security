package com.davidorellana.jwtloginauthspringsecurity.user.repository;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.model.UserDto;
import com.davidorellana.jwtloginauthspringsecurity.user.repository.mongorepository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryDao {

    private final UserMongoRepository userMongoRepository;

    @Autowired
    public UserRepositoryImpl(UserMongoRepository userMongoRepository) {
        this.userMongoRepository = userMongoRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userMongoRepository.findAll();
    }

    @Override
    public User findUserById(String id) {
        Optional<User> userByIdFound = userMongoRepository.findById(id);
        if (userByIdFound.isPresent()) {
            return userByIdFound.get();
        }
        return null;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        return userMongoRepository.save(user);

    }

    @Override
    public User updateUserById(String id, UserDto userDto) {
        User userFound = findUserById(id);
        if (userFound != null) {
            userFound.updateUserCollection(userDto);
            return userMongoRepository.save(userFound);
        }
        return null;
    }

    @Override
    public Boolean deleteUserById(String id) {
        User userFound = findUserById(id);
        if (userMongoRepository.existsById(id)) {
            userMongoRepository.delete(userFound);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllUsers() {
        List<User> usersFound = findAllUsers();
        userMongoRepository.deleteAll(usersFound);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> userByEmailFound = userMongoRepository.findUserByEmail(email);
        if (userByEmailFound.isPresent()) {
            return userByEmailFound.get();
        }
        return null;
    }
}
