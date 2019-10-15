package com.java.microservice.micronautconsumer.repository;

import com.java.microservice.core.domain.PersonAggregationData;
import com.java.microservice.micronautconsumer.domain.Person;
import com.mongodb.reactivestreams.client.MongoClient;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.bson.Document;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.gte;
import static java.util.List.of;
import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

@Singleton
public class PersonRepositoryImpl extends AbstractMongoRepository<Person> implements PersonRepository {
    private static final String COLLECTION_NAME = "person";

    @Inject
    public PersonRepositoryImpl(final MongoClient mongoClient) {
        super(mongoClient, COLLECTION_NAME, Person.class);
    }

    @Override
    public Single<List<PersonAggregationData>> getAggregation() {
        return Flowable
                .fromPublisher(
                        getCollection().aggregate(
                                of(
                                        match(gte("age", 0)),
                                        group(new Document().append("country", "$country").append("gender", "$gender"),
                                                first("country", "$country"),
                                                first("gender", "$gender"),
                                                avg("averageSalary", "$salary"),
                                                avg("averageAge", "$age")
                                        )

                                ), PersonAggregationData.class
                        )
                )
                .collectInto(newArrayList(), List::add);
    }

    @Override
    public Single<Person> save(final Person person) {
        return Single.fromPublisher(getCollection().insertOne(person))
                .map(success -> person);
    }
}
