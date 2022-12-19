package com.davidorellana.jwtloginauthspringsecurity.auth.controller;

import com.davidorellana.jwtloginauthspringsecurity.auth.dto.LoginDto;
import com.davidorellana.jwtloginauthspringsecurity.auth.dto.TokenDto;
import com.davidorellana.jwtloginauthspringsecurity.auth.security.jwt.OperationJwt;
import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final OperationJwt operationJwt;
    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    public AuthenticationController(OperationJwt operationJwt, UserService userService) {
        this.operationJwt = operationJwt;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> generateJwt(@RequestBody LoginDto loginDto) {
        User userFound = userService.findUserByEmail(loginDto.getEmail());
        if (userFound != null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            TokenDto tokenDto = operationJwt.generateTokenDto(userFound);
            return new ResponseEntity<>(tokenDto, HttpStatus.OK);
        }
        return new ResponseEntity("Email " + loginDto.getEmail() + " not found", HttpStatus.NOT_FOUND);
    }
}
