package com.java.microservice.springbootproducer.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ContextConfiguration {
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
