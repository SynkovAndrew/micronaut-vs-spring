package com.java.microservice.core.cloud.feign;

import com.java.microservice.core.cloud.MicronautConsumerResource;
import com.java.microservice.core.cloud.SpringBootConsumerResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("micronaut-consumer")
public interface MicronautConsumerClient extends MicronautConsumerResource {
}
