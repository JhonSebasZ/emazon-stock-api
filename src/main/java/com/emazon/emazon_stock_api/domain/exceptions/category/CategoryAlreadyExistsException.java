package com.emazon.emazon_stock_api.domain.exceptions.category;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
