package com.emazon.emazon_stock_api.domain.exceptions.category;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
