package com.java.microservice.micronautconsumer.service;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.micronautconsumer.domain.Person;
import com.java.microservice.micronautconsumer.repository.PersonRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Slf4j
public class PersonService {
    private final PersonRepository repository;
    private final MappingService mappingService;

    @Inject
    public PersonService(final PersonRepository repository,
                         final MappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    public Single<PersonDTO> save(final PersonDTO request) {
        log.info("Saving person: {}", request);
        return repository.save(mappingService.map(request, Person.class))
                .map(person -> mappingService.map(person, PersonDTO.class));
    }

    public Flowable<PersonDTO> findByFirstName(final String firstName) {
        log.info("Searching for person with name: {}", firstName);
        return repository.findByFirstName(firstName)
                .map(person -> mappingService.map(person, PersonDTO.class));
    }

    public Single<List<PersonAggregationDataDTO>> getAggregation() {
        log.info("Aggregating...");
        return repository.getAggregation()
                .map(aggregation -> mappingService.mapAsList(aggregation, PersonAggregationDataDTO.class));
    }
}
