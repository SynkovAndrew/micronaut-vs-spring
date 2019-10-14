package com.java.microservice.springbootconsumer.repository;

import com.java.microservice.springbootconsumer.domain.Person;
import com.java.microservice.core.domain.PersonAggregationData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@RequiredArgsConstructor
public class PersonCustomRepositoryImpl implements PersonCustomRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<List<PersonAggregationData>> getAggregation() {
        final Aggregation aggregation = newAggregation(
                match(Criteria.where("age").gte(0)),
                group("country", "gender")
                        .avg("salary").as("averageSalary")
                        .avg("age").as("averageAge")
                        .first("gender").as("gender")
                        .first("country").as("country")
        );

        return mongoTemplate.aggregate(aggregation, Person.class, PersonAggregationData.class)
                .collectList();
    }
}