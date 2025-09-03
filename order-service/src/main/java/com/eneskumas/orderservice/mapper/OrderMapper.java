package com.eneskumas.orderservice.mapper;

import com.eneskumas.orderservice.dto.OrderResponseDto;
import com.eneskumas.orderservice.dto.OrderUpdateRequestDto;
import com.eneskumas.orderservice.entity.Order;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> ordersToOrderResponseDtoList(List<Order> orders);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void orderUpdateFromOrderUpdateRequestDto(OrderUpdateRequestDto orderUpdateRequestDto, @MappingTarget Order order);

}
