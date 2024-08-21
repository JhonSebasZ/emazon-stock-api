package com.emazon.emazon_stock_api.infrastructure.exceptionHandler;

import com.emazon.emazon_stock_api.domain.exceptions.BrandExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class BrandControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(BrandExceptions.InvalidBrandNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleInvalidBrandNameException(BrandExceptions.InvalidBrandNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage())
        );
    }

    @ExceptionHandler(BrandExceptions.InvalidBrandDescriptionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handlerInvalidBrandDescriptionException(BrandExceptions.InvalidBrandDescriptionException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(BrandExceptions.ExistingBrandException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Map<String, String>> handlerExistingBrandException(BrandExceptions.ExistingBrandException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }
}
