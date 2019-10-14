package com.java.microservice.micronautconsumer.domain;

import com.java.microservice.core.dto.Country;
import com.java.microservice.core.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer salary;
    private Gender gender;
    private Country country;
}
