package com.java.microservice.springbootproducer.service;


import com.github.javafaker.Faker;
import com.java.microservice.core.dto.Country;
import com.java.microservice.core.dto.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PersonPropertyGenerator {
    private final int MAX_AGE = 100;
    private final int MIN_AGE = 20;
    private final int MAX_SALARY = 150000;
    private final int MIN_SALARY = 30000;
    private final Random random;
    private final Faker faker;

    public String generateFirstName() {
        return faker.name().firstName();
    }

    public String generateLastName() {
        return faker.name().lastName();
    }

    public int generateAge() {
        return generateRandomIntIntRange(MIN_AGE, MAX_AGE);
    }

    public int generateSalary() {
        return generateRandomIntIntRange(MIN_SALARY, MAX_SALARY);
    }

    public Gender generateGender() {
        return Gender.values()[random.nextInt(2)];
    }

    public Country generateCountry() {
        return Country.values()[random.nextInt(10)];
    }

    private int generateRandomIntIntRange(final int min, final int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
