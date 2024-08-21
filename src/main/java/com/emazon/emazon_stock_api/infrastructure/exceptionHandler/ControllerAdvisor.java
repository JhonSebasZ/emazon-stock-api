package com.emazon.emazon_stock_api.infrastructure.exceptionHandler;

import com.emazon.emazon_stock_api.domain.exceptions.category.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(InvalidCategoryNameException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoryNameException(InvalidCategoryNameException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(InvalidCategoryDescriptionException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCategoryNameException(InvalidCategoryDescriptionException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNameAlreadyExistsException(CategoryAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }
}
