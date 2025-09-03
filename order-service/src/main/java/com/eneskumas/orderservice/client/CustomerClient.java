package com.eneskumas.orderservice.client;

import com.eneskumas.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", configuration = CustomerClientConfig.class)
public interface CustomerClient {

    @GetMapping("/api/customers/{id}") Customer getById(@PathVariable Long id);

}
