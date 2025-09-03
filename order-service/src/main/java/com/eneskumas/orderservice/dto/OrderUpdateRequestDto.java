package com.eneskumas.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequestDto {

    private Long customerId;

    private String name;

    private BigDecimal price;

    private Integer quantity;

}
