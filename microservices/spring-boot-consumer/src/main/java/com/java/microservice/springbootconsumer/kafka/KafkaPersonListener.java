package com.java.microservice.springbootconsumer.kafka;

import com.java.microservice.core.dto.PersonDTO;
import com.java.microservice.springbootconsumer.domain.Person;
import com.java.microservice.springbootconsumer.repository.PersonRepository;
import com.java.microservice.springbootconsumer.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.java.microservice.core.configuration.Constant.SPRING_BOOT_CONSUMER_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPersonListener {
    private final PersonRepository repository;
    private final MappingService mappingService;

    @KafkaListener(id = "spring-boot-consumer-listener", topics = SPRING_BOOT_CONSUMER_TOPIC, containerFactory = "singleFactory")
    public void consume(final PersonDTO dto) {
        repository.save(mappingService.map(dto, Person.class))
                .doOnError(error -> log.info("Failed to save person!"))
                .subscribe(person -> log.info("Person: {} 's been received from Micronaut and saved!", person));
    }
}
