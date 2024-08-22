package com.emazon.emazon_stock_api.infrastructure.exceptions.pagination;

public class ExceedsNumberOfPageException extends RuntimeException{
    public ExceedsNumberOfPageException(String message) {
        super(message);
    }
}
