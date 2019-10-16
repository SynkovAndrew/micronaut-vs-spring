package com.java.microservice.springbootproducer.service;

import com.java.microservice.core.cloud.rx.MicronautConsumerRxClient;
import com.java.microservice.core.cloud.rx.SpringBootConsumerRxClient;
import com.java.microservice.core.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonProducerService {
    private static final int REQUEST_COUNT = 2;
    private static final int THREAD_COUNT = 2;
    private final PersonPropertyGenerator generator;
    private final SpringBootConsumerRxClient springBootConsumerClient;
    private final MicronautConsumerRxClient micronautConsumerClient;
    private final ExecutorService executorService = newFixedThreadPool(THREAD_COUNT);

    private PersonDTO createPerson() {
        return PersonDTO.builder()
                .age(generator.generateAge())
                .salary(generator.generateSalary())
                .firstName(generator.generateFirstName())
                .lastName(generator.generateLastName())
                .country(generator.generateCountry())
                .gender(generator.generateGender())
                .build();
    }

    @PostConstruct
    public void init() throws InterruptedException {
        Thread.sleep(7000);
        executorService.submit(() -> {
            for (int i = 0; i < REQUEST_COUNT; i++) {
                springBootConsumerClient.save(createPerson())
                        .doOnError((error) -> log.error(error.getMessage()))
                        .subscribe((response) -> log.info("Person's been sent: {} to spring boot consumer", response));
            }
        });
        executorService.submit(() -> {
            for (int i = 0; i < REQUEST_COUNT; i++) {
                micronautConsumerClient.save(createPerson())
                        .doOnError((error) -> log.error(error.getMessage()))
                        .subscribe((response) -> log.info("Person's been sent: {} micronaut consumer", response));
            }
        });
    }
}
