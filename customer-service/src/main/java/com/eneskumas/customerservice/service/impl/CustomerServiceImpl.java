package com.eneskumas.customerservice.service.impl;

import com.eneskumas.customerservice.dto.CustomerCreateDto;
import com.eneskumas.customerservice.dto.CustomerResponseDto;
import com.eneskumas.customerservice.dto.CustomerUpdateDto;
import com.eneskumas.customerservice.entity.Customer;
import com.eneskumas.customerservice.exception.CustomerAlreadyExistsException;
import com.eneskumas.customerservice.exception.CustomerNotFoundException;
import com.eneskumas.customerservice.mapper.CustomerMapper;
import com.eneskumas.customerservice.repository.CustomerRepository;
import com.eneskumas.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDto> getAll() {

        final List<Customer> customers = customerRepository.findAll();

        return customerMapper.customersToCustomerResponseDtoList(customers);
    }

    @Override
    public CustomerResponseDto getById(Long id) {

        final Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        return customerMapper.customerToCustomerResponseDto(customer);
    }

    @Override
    public CustomerResponseDto create(CustomerCreateDto dto) {

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new CustomerAlreadyExistsException(dto.getEmail());
        }

        final Customer customer = Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .location(dto.getLocation())
                .build();

        return customerMapper.customerToCustomerResponseDto(customerRepository.save(customer));
    }

    @Override
    public CustomerResponseDto update(Long id, CustomerUpdateDto dto) {

        if (customerRepository.existsByEmailAndIdNot(dto.getEmail(), id)) {
            throw new CustomerAlreadyExistsException(dto.getEmail());
        }

        final Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerMapper.customerUpdateFromCustomerUpdateDto(dto, customer);

        return customerMapper.customerToCustomerResponseDto(customerRepository.save(customer));
    }

    @Override
    public void delete(Long id) {

        final Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        customerRepository.delete(customer);
    }

}
