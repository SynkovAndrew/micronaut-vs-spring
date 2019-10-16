package com.java.microservice.micronautconsumer.service;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.micronautconsumer.domain.Person;
import com.java.microservice.micronautconsumer.kafka.PersonKafkaClient;
import com.java.microservice.micronautconsumer.repository.PersonRepository;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Slf4j
public class PersonService {
    private final MappingService mappingService;
    private final PersonRepository repository;
    private final PersonKafkaClient personKafkaClient;

    @Inject
    public PersonService(final PersonRepository repository,
                         final MappingService mappingService,
                         final PersonKafkaClient personKafkaClient) {
        this.repository = repository;
        this.mappingService = mappingService;
        this.personKafkaClient = personKafkaClient;
    }

    public Single<List<PersonAggregationDataDTO>> getAggregation() {
        log.info("Aggregating...");
        return repository.getAggregation()
                .map(aggregation -> mappingService.mapAsList(aggregation, PersonAggregationDataDTO.class));
    }

    public Single<PersonDTO> save(final PersonDTO request) {
        return repository.save(mappingService.map(request, Person.class))
                .map(saved -> {
                    log.info("Person: {} 's been saved!", request);
                    final PersonDTO person = mappingService.map(saved, PersonDTO.class);
                    personKafkaClient.send(person);
                    return person;
                });
    }
}
