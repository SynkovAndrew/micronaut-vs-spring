package com.java.microservice.springbootproducer.configuration;

import com.java.microservice.core.cloud.feign.MicronautConsumerClient;
import com.java.microservice.core.cloud.feign.SpringBootConsumerClient;
import com.java.microservice.core.cloud.rx.MicronautConsumerRxClient;
import com.java.microservice.core.cloud.rx.SpringBootConsumerRxClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.java.microservice.core.cloud.feign")
public class FeignConfiguration {

    @Bean
    public MicronautConsumerRxClient micronautConsumerRxClient(final MicronautConsumerClient client) {
        return new MicronautConsumerRxClient(client);
    }

    @Bean
    public SpringBootConsumerRxClient springBootConsumerRxClient(final SpringBootConsumerClient client) {
        return new SpringBootConsumerRxClient(client);
    }
}
