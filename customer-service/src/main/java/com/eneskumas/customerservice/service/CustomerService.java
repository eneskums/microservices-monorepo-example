package com.eneskumas.customerservice.service;

import com.eneskumas.customerservice.dto.CustomerCreateDto;
import com.eneskumas.customerservice.dto.CustomerResponseDto;
import com.eneskumas.customerservice.dto.CustomerUpdateDto;
import com.eneskumas.customerservice.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<CustomerResponseDto> getAll();

    CustomerResponseDto getById(Long id);

    CustomerResponseDto create(CustomerCreateDto customer);

    CustomerResponseDto update(Long id, CustomerUpdateDto customer);

    void delete(Long id);

}
