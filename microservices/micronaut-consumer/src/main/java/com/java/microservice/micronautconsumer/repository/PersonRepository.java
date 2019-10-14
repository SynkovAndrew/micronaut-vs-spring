package com.java.microservice.micronautconsumer.repository;

import com.java.microservice.core.domain.PersonAggregationData;
import com.java.microservice.micronautconsumer.domain.Person;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;

public interface PersonRepository {
    Single<Person> save(Person person);

    Flowable<Person> findByFirstName(String firstName);

    Single<List<PersonAggregationData>> getAggregation();
}
