package com.eneskumas.orderservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderResponseDto {

    private Long id;

    private Long customerId;

    private String name;

    private BigDecimal price;

    private Integer quantity;

}
