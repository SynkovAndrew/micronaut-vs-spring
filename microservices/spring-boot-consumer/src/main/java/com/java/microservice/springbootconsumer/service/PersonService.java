package com.java.microservice.springbootconsumer.service;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.springbootconsumer.domain.Person;
import com.java.microservice.springbootconsumer.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {
    private final MappingService mappingService;
    private final PersonRepository repository;

    public Mono<List<PersonAggregationDataDTO>> getAggregation() {
        log.info("Aggregating...");
        return repository.getAggregation()
                .map(aggregation -> mappingService.mapAsList(aggregation, PersonAggregationDataDTO.class));
    }

    public Mono<PersonDTO> save(final PersonDTO request) {
        log.info("Saving person: {}", request);
        return repository.save(mappingService.map(request, Person.class))
                .map(person -> mappingService.map(person, PersonDTO.class));
    }
}
