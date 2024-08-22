package com.emazon.emazon_stock_api.infrastructure.exceptionHandler;

import com.emazon.emazon_stock_api.application.Exceptions.pagination.InvalidOrder;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.NumberPageNegativeException;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.WrongSizeException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.EmptyPageException;
import com.emazon.emazon_stock_api.infrastructure.exceptions.pagination.ExceedsNumberOfPageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class PaginationControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(InvalidOrder.class)
    public ResponseEntity<Map<String, String>> handleInvalidOrder(InvalidOrder ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(NumberPageNegativeException.class)
    public ResponseEntity<Map<String, String>> handleNumberPageNegativeException(NumberPageNegativeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(WrongSizeException.class)
    public ResponseEntity<Map<String, String>> handleWrongSizeException(WrongSizeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(ExceedsNumberOfPageException.class)
    public ResponseEntity<Map<String, String>> handleExceedsNumberOfPageException(ExceedsNumberOfPageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(EmptyPageException.class)
    public ResponseEntity<Map<String, String>> handleEmptyPageException(EmptyPageException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                Collections.singletonMap(MESSAGE, ex.getMessage()));
    }
}
