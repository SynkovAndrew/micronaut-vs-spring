package com.java.microservice.springbootconsumer.service;

import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.springbootconsumer.domain.Person;
import com.java.microservice.springbootconsumer.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.java.microservice.core.configuration.Constant.MICRONAUT_CONSUMER_TOPIC;
import static com.java.microservice.core.configuration.Constant.SPRING_BOOT_CONSUMER_TOPIC;


@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {
    private final MappingService mappingService;
    private final PersonRepository repository;
    private final KafkaTemplate<Long, PersonDTO> kafkaTemplate;

    public Mono<List<PersonAggregationDataDTO>> getAggregation() {
        log.info("Aggregating...");
        return repository.getAggregation()
                .map(aggregation -> mappingService.mapAsList(aggregation, PersonAggregationDataDTO.class));
    }

    public Mono<PersonDTO> save(final PersonDTO request) {
        return repository.save(mappingService.map(request, Person.class))
                .map(saved -> {
                    log.info("Person: {} 's been saved!", request);
                    final PersonDTO person = mappingService.map(saved, PersonDTO.class);
                    kafkaTemplate.send(MICRONAUT_CONSUMER_TOPIC, person);
                    return person;
                });
    }
}
