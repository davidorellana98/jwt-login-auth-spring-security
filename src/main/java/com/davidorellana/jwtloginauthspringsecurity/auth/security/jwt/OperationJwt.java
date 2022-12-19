package com.davidorellana.jwtloginauthspringsecurity.auth.security.jwt;

import com.davidorellana.jwtloginauthspringsecurity.auth.dto.TokenDto;
import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import io.jsonwebtoken.Claims;

import java.util.Calendar;

public interface OperationJwt {

    String generateJwt(User user, Calendar expirationDate);
    TokenDto generateTokenDto(User user);
    Boolean validateJwt(String jwt, User user);
    Claims returnClaims(String jwt);
    String extractSubject(String jwt);
}
