package com.java.microservice.core.cloud.rx;

import com.java.microservice.core.cloud.feign.SpringBootConsumerClient;
import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

import java.util.List;

import static rx.Observable.fromCallable;

public class SpringBootConsumerRxClient {
    private final SpringBootConsumerClient client;

    public SpringBootConsumerRxClient(SpringBootConsumerClient client) {
        this.client = client;
    }

    public Observable<PersonDTO> save(final PersonDTO request) {
        return new SavePersonObservableCommand(client, request).toObservable();
    }

    public Observable<PersonDTO> findByFirstName(final String firstName) {
        return new FindByFirstNameObservableCommand(client, firstName).toObservable();
    }

    public Observable<List<PersonAggregationDataDTO>> getAggregation() {
        return new GetAggregationObservableCommand(client).toObservable();
    }

    class SavePersonObservableCommand extends HystrixObservableCommand<PersonDTO> {
        private final SpringBootConsumerClient client;
        private final PersonDTO request;

        SavePersonObservableCommand(final SpringBootConsumerClient client,
                                    final PersonDTO request) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.client = client;
            this.request = request;
        }

        @Override
        protected Observable<PersonDTO> construct() {
            return fromCallable(() -> client.save(request));
        }
    }

    class FindByFirstNameObservableCommand extends HystrixObservableCommand<PersonDTO> {
        private final SpringBootConsumerClient client;
        private final String firstName;

        FindByFirstNameObservableCommand(final SpringBootConsumerClient client,
                                         final String firstName) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.client = client;
            this.firstName = firstName;
        }

        @Override
        protected Observable<PersonDTO> construct() {
            return fromCallable(() -> client.findByFirstName(firstName));
        }
    }

    class GetAggregationObservableCommand extends HystrixObservableCommand<List<PersonAggregationDataDTO>> {
        private final SpringBootConsumerClient client;

        GetAggregationObservableCommand(final SpringBootConsumerClient client) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.client = client;
        }

        @Override
        protected Observable<List<PersonAggregationDataDTO>> construct() {
            return fromCallable(client::getAggregation);
        }
    }
}
