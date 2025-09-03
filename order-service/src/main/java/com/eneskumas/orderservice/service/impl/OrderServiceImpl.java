package com.eneskumas.orderservice.service.impl;

import com.eneskumas.orderservice.dto.OrderCreateRequestDto;
import com.eneskumas.orderservice.dto.OrderResponseDto;
import com.eneskumas.orderservice.dto.OrderUpdateRequestDto;
import com.eneskumas.orderservice.entity.Order;
import com.eneskumas.orderservice.exception.CustomerNotFoundException;
import com.eneskumas.orderservice.exception.OrderNotFoundException;
import com.eneskumas.orderservice.mapper.OrderMapper;
import com.eneskumas.orderservice.model.Customer;
import com.eneskumas.orderservice.repository.OrderRepository;
import com.eneskumas.orderservice.service.CustomerService;
import com.eneskumas.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerService customerService;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderResponseDto> getAll() {

        final List<Order> orders = orderRepository.findAll();

        return orderMapper.ordersToOrderResponseDtoList(orders);
    }

    @Override
    public OrderResponseDto getById(Long id) {

        final Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        return orderMapper.orderToOrderResponseDto(order);
    }

    @Override
    public List<OrderResponseDto> getByCustomerId(Long customerId) {

        validateCustomer(customerId);

        final List<Order> orders = orderRepository.findByCustomerId(customerId);

        return orderMapper.ordersToOrderResponseDtoList(orders);
    }

    @Override
    @Transactional
    public OrderResponseDto create(OrderCreateRequestDto orderCreateRequestDto) {

        validateCustomer(orderCreateRequestDto.getCustomerId());

        final Order newOrder = Order.builder()
                .name(orderCreateRequestDto.getName())
                .customerId(orderCreateRequestDto.getCustomerId())
                .price(orderCreateRequestDto.getPrice())
                .quantity(orderCreateRequestDto.getQuantity())
                .build();

        final Order savedOrder = orderRepository.save(newOrder);

        return orderMapper.orderToOrderResponseDto(savedOrder);
    }

    @Override
    @Transactional
    public OrderResponseDto update(Long id, OrderUpdateRequestDto orderUpdateRequestDto) {

        validateCustomer(orderUpdateRequestDto.getCustomerId());

        final Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        orderMapper.orderUpdateFromOrderUpdateRequestDto(orderUpdateRequestDto, order);

        final Order updatedOrder = orderRepository.save(order);

        return orderMapper.orderToOrderResponseDto(updatedOrder);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        final Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(order);
    }

    private void validateCustomer(Long customerId) {
        final Customer customer = customerService.getCustomer(customerId);

        if (Objects.isNull(customer)) {
            throw new CustomerNotFoundException(customerId);
        }
    }

}
