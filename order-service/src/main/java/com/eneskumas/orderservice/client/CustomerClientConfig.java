package com.eneskumas.orderservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(ObjectMapper mapper) {

        return new FeignCustomerErrorDecoder(mapper);
    }

}

