package com.eneskumas.customerservice.dto;

import lombok.*;

@Data
public class CustomerResponseDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String location;

}
