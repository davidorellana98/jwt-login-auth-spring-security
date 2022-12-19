package com.davidorellana.jwtloginauthspringsecurity.auth.service;

import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.repository.UserRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceAuth implements UserDetailsService {

    @Autowired
    private UserRepositoryDao userRepositoryDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userFound = userRepositoryDao.findUserByEmail(email);
        return new org.springframework.security.core.userdetails.User(
                userFound.getEmail(), "{noop}" + userFound.getPassword(), new ArrayList<>()
        );
    }
}
