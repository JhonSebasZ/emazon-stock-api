package com.emazon.emazon_stock_api.application.Exceptions.pagination;

public class NumberPageNegativeException extends RuntimeException{
    public NumberPageNegativeException(String message) {
        super(message);
    }
}
