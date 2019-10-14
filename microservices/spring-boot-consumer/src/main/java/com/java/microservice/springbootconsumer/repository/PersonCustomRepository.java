package com.java.microservice.springbootconsumer.repository;

import com.java.microservice.core.domain.PersonAggregationData;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonCustomRepository {
    Mono<List<PersonAggregationData>> getAggregation();
}