package com.davidorellana.jwtloginauthspringsecurity.auth.security.jwt;

import com.davidorellana.jwtloginauthspringsecurity.auth.service.UserDetailServiceAuth;
import com.davidorellana.jwtloginauthspringsecurity.user.model.User;
import com.davidorellana.jwtloginauthspringsecurity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private OperationJwt operationJwt;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailServiceAuth userDetailServiceAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String idUser = operationJwt.extractSubject(jwt);

            if (idUser != null && SecurityContextHolder.getContext().getAuthentication() == null){
                User userFound = userService.findUserById(idUser);

                if (operationJwt.validateJwt(jwt, userFound)){
                    UserDetails userDetails = userDetailServiceAuth.loadUserByUsername(userFound.getEmail());
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails
                                    ,null
                                    ,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                            .buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
