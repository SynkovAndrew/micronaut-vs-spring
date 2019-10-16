package com.java.microservice.micronautconsumer.kafka;

import com.java.microservice.core.dto.PersonDTO;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

import static com.java.microservice.core.configuration.Constant.SPRING_BOOT_CONSUMER_TOPIC;

@KafkaClient
public interface PersonKafkaClient {
    @Topic(SPRING_BOOT_CONSUMER_TOPIC)
    void send(PersonDTO person);
}
