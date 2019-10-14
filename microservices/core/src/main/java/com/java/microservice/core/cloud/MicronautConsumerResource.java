package com.java.microservice.core.cloud;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("micronaut-consumer/person")
public interface MicronautConsumerResource {

    @PostMapping
    PersonDTO save(@RequestBody PersonDTO person);

    @GetMapping("{firstName}")
    PersonDTO findByFirstName(@PathVariable("firstName") String firstName);

    @GetMapping("/aggregate")
    List<PersonAggregationDataDTO> getAggregation();
}
