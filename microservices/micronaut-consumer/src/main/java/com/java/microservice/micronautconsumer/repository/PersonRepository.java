package com.java.microservice.micronautconsumer.repository;

import com.java.microservice.core.domain.PersonAggregationData;
import com.java.microservice.micronautconsumer.domain.Person;
import io.reactivex.Single;

import java.util.List;

public interface PersonRepository {
    Single<List<PersonAggregationData>> getAggregation();

    Single<Person> save(Person person);
}
