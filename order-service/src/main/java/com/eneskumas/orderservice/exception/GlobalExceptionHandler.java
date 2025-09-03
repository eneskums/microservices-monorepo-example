package com.eneskumas.orderservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleOrderNotFoundException(OrderNotFoundException e) {

        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Order Not Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleCustomerNotFoundException(CustomerNotFoundException e) {

        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Customer Not Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(CustomerServiceException.class)
    public ResponseEntity<ProblemDetail> handleCustomerServiceException(CustomerServiceException e) {

        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.SERVICE_UNAVAILABLE);
        problemDetail.setTitle("Customer Service Temporarily Unavailable");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleGenericErrors(Exception e) {

        final ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

}
