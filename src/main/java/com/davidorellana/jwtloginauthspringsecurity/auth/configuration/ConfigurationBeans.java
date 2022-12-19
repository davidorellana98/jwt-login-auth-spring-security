package com.davidorellana.jwtloginauthspringsecurity.auth.configuration;

import com.davidorellana.jwtloginauthspringsecurity.auth.security.jwt.OperationJwt;
import com.davidorellana.jwtloginauthspringsecurity.auth.security.jwt.OperationJwtImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBeans {

    @Bean
    public OperationJwt generationToken() {
        return new OperationJwtImpl();
    }
}
