package com.davidorellana.jwtloginauthspringsecurity.user.repository.mongorepository;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
