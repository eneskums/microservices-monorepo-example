package com.eneskumas.customerservice.mapper;

import com.eneskumas.customerservice.dto.CustomerResponseDto;
import com.eneskumas.customerservice.dto.CustomerUpdateDto;
import com.eneskumas.customerservice.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto customerToCustomerResponseDto(Customer customer);

    List<CustomerResponseDto> customersToCustomerResponseDtoList(List<Customer> customers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void customerUpdateFromCustomerUpdateDto(CustomerUpdateDto customerUpdateDto, @MappingTarget Customer customer);

}
