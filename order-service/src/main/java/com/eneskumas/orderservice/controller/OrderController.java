package com.eneskumas.orderservice.controller;

import com.eneskumas.orderservice.dto.OrderCreateRequestDto;
import com.eneskumas.orderservice.dto.OrderResponseDto;
import com.eneskumas.orderservice.dto.OrderUpdateRequestDto;
import com.eneskumas.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAll() {

        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {

        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponseDto>> getByCustomerId(@PathVariable Long customerId) {

        return ResponseEntity.ok(orderService.getByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateRequestDto orderCreateRequestDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(orderCreateRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable Long id,
                                                   @RequestBody OrderUpdateRequestDto orderUpdateRequestDto) {

        return ResponseEntity.ok(orderService.update(id, orderUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
