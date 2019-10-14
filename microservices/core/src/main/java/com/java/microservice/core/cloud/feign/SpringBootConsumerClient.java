package com.java.microservice.core.cloud.feign;

import com.java.microservice.core.cloud.SpringBootConsumerResource;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("spring-boot-consumer")
public interface SpringBootConsumerClient extends SpringBootConsumerResource {
}
