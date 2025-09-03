package com.eneskumas.orderservice.service;

import com.eneskumas.orderservice.dto.OrderCreateRequestDto;
import com.eneskumas.orderservice.dto.OrderResponseDto;
import com.eneskumas.orderservice.dto.OrderUpdateRequestDto;

import java.util.List;

public interface OrderService {

    List<OrderResponseDto> getAll();

    OrderResponseDto getById(Long id);

    List<OrderResponseDto> getByCustomerId(Long customerId);

    OrderResponseDto create(OrderCreateRequestDto orderCreateRequestDto);

    OrderResponseDto update(Long id, OrderUpdateRequestDto orderUpdateRequestDto);

    void delete(Long id);

}
