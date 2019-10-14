package com.java.microservice.micronautconsumer.repository;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class AbstractMongoRepository<T> {
    private static final String DATABASE_NAME = "micronaut-consumer";
    protected final MongoClient mongoClient;
    protected final String collectionName;
    private final Class<T> clazz;

    MongoCollection<T> getCollection() {
        return mongoClient.getDatabase(DATABASE_NAME).getCollection(collectionName, clazz);
    }
}
