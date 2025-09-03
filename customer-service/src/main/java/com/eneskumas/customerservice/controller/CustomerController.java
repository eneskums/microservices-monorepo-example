package com.eneskumas.customerservice.controller;

import com.eneskumas.customerservice.dto.CustomerCreateDto;
import com.eneskumas.customerservice.dto.CustomerResponseDto;
import com.eneskumas.customerservice.dto.CustomerUpdateDto;
import com.eneskumas.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAll() {

        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@RequestBody CustomerCreateDto customerCreateDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerCreateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id,
                                                      @RequestBody CustomerUpdateDto customerUpdateDto) {

        return ResponseEntity.ok(customerService.update(id, customerUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
