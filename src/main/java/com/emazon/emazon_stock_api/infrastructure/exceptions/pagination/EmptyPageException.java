package com.emazon.emazon_stock_api.infrastructure.exceptions.pagination;

public class EmptyPageException extends RuntimeException{
    public EmptyPageException(String message) {
        super(message);
    }
}
