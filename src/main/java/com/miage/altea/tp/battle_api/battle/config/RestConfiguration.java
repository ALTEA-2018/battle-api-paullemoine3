package com.miage.altea.tp.battle_api.battle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {

    @Value("${trainer.service.username}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Bean
    RestTemplate trainerApiRestTemplate(){
        BasicAuthenticationInterceptor basicAuthInt = new BasicAuthenticationInterceptor(username, password);
        RestTemplate rest = new RestTemplate();
        rest.setInterceptors(Arrays.asList(basicAuthInt));
        return rest;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}

