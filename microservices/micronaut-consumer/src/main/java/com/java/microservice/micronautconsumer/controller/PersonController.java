package com.java.microservice.micronautconsumer.controller;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.micronautconsumer.service.PersonService;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@Controller("/micronaut-consumer/person")
public class PersonController {
    private final PersonService personService;

    @Inject
    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @Post
    public Single<PersonDTO> save(final @Body PersonDTO person) {
        return personService.save(person);
    }

    @Get("/aggregate")
    public Single<List<PersonAggregationDataDTO>> getAggregation() {
        return personService.getAggregation();
    }
}
