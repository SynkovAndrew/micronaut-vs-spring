package com.java.microservice.core.cloud.rx;

import com.java.microservice.core.cloud.feign.MicronautConsumerClient;
import com.java.microservice.core.dto.PersonAggregationDataDTO;
import com.java.microservice.core.dto.PersonDTO;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;

import java.util.List;

import static rx.Observable.fromCallable;

public class MicronautConsumerRxClient {
    private final MicronautConsumerClient client;

    public MicronautConsumerRxClient(MicronautConsumerClient client) {
        this.client = client;
    }

    public Observable<PersonDTO> save(final PersonDTO request) {
        return new SavePersonObservableCommand(client, request).toObservable();
    }

    public Observable<List<PersonAggregationDataDTO>> getAggregation() {
        return new GetAggregationObservableCommand(client).toObservable();
    }

    public Observable<PersonDTO> findByFirstName(final String firstName) {
        return new FindByFirstNameObservableCommand(client, firstName).toObservable();
    }

    class SavePersonObservableCommand extends HystrixObservableCommand<PersonDTO> {
        private final MicronautConsumerClient client;
        private final PersonDTO request;

        SavePersonObservableCommand(final MicronautConsumerClient client,
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
        private final MicronautConsumerClient client;
        private final String firstName;

        FindByFirstNameObservableCommand(final MicronautConsumerClient client,
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
        private final MicronautConsumerClient client;

        GetAggregationObservableCommand(final MicronautConsumerClient client) {
            super(HystrixCommandGroupKey.Factory.asKey("default"));
            this.client = client;
        }

        @Override
        protected Observable<List<PersonAggregationDataDTO>> construct() {
            return fromCallable(client::getAggregation);
        }
    }
}
