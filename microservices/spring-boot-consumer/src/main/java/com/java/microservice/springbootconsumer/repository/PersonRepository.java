package com.java.microservice.springbootconsumer.repository;

import com.java.microservice.springbootconsumer.domain.Person;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person, ObjectId>, PersonCustomRepository {
}