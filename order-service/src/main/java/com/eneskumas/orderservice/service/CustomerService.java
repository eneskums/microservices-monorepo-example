package com.eneskumas.orderservice.service;

import com.eneskumas.orderservice.client.CustomerClient;
import com.eneskumas.orderservice.exception.CustomerNotFoundException;
import com.eneskumas.orderservice.exception.CustomerServiceException;
import com.eneskumas.orderservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerClient customerClient;

    @CircuitBreaker(name = "CustomerServiceCB", fallbackMethod = "fallbackGetCustomer")
    public Customer getCustomer(Long id) {

        return customerClient.getById(id);
    }

    public Customer fallbackGetCustomer(Long id, Throwable t) {

        if (t instanceof CustomerNotFoundException) {
            throw (CustomerNotFoundException) t;
        }
        throw new CustomerServiceException("Customer Service Temporarily Unavailable. Please Try Again Later");
    }

}
