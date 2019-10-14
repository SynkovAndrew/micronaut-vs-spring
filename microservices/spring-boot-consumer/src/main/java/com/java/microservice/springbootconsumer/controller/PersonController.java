package com.java.microservice.springbootconsumer.controller;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.springbootconsumer.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/spring-boot-consumer/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public Mono<PersonDTO> save(@RequestBody final PersonDTO person) {
        return personService.save(person);
    }

    @GetMapping("{firstName}")
    public Mono<PersonDTO> findByFirstName(@PathVariable("firstName") final String firstName) {
        return personService.findByFirstName(firstName);
    }

    @GetMapping("/aggregate")
    public Mono<List<PersonAggregationDataDTO>> getAggregation() {
        return personService.getAggregation();
    }
}

