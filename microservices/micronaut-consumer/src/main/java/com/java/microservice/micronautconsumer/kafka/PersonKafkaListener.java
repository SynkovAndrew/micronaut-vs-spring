package com.java.microservice.micronautconsumer.kafka;

import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.micronautconsumer.domain.Person;
import com.java.microservice.micronautconsumer.repository.PersonRepository;
import com.java.microservice.micronautconsumer.service.MappingService;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;

import static com.java.microservice.core.configuration.Constant.MICRONAUT_CONSUMER_TOPIC;

@Slf4j
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class PersonKafkaListener {
    private final MappingService mappingService;
    private final PersonRepository repository;

    @Inject
    public PersonKafkaListener(final PersonRepository repository,
                               final MappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Topic(MICRONAUT_CONSUMER_TOPIC)
    public void receive(final PersonDTO dto) {
        repository.save(mappingService.map(dto, Person.class))
                .doOnError(error -> log.info("Failed to save person!"))
                .subscribe(person -> log.info("Person: {} 's been received from Spring Boot and saved!", person))
                .dispose();
    }
}