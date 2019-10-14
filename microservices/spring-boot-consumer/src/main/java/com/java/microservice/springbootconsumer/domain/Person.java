package com.java.microservice.springbootconsumer.domain;

import com.java.microservice.core.dto.Country;
import com.java.microservice.core.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    private String id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private Integer age;
    @NotNull
    private Integer salary;
    @NotNull
    private Gender gender;
    @NotNull
    private Country country;
}
