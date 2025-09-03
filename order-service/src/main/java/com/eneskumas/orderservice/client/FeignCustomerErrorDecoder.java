package com.eneskumas.orderservice.client;

import com.eneskumas.orderservice.model.ExceptionMessage;
import com.eneskumas.orderservice.exception.CustomerNotFoundException;
import com.eneskumas.orderservice.exception.CustomerServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class FeignCustomerErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        ExceptionMessage exceptionMessage = null;

        try (InputStream bodyIs = Objects.nonNull(response.body()) ? response.body().asInputStream() : null) {
            if (Objects.nonNull(bodyIs)) {
                exceptionMessage = mapper.readValue(bodyIs, ExceptionMessage.class);
            }
        } catch (IOException e) {
            return new CustomerServiceException("Failed to parse error response: " + e.getMessage());
        }

        final String message = Optional.ofNullable(exceptionMessage)
                .map(ExceptionMessage::getDetail)
                .orElse("Unexpected error from customer service");

        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            return new CustomerNotFoundException(message);
        }
        return new CustomerServiceException(message + " (status=" + response.status() + ")");
    }

}
