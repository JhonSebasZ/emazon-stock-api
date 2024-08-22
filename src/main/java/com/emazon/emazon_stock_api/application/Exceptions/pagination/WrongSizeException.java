package com.emazon.emazon_stock_api.application.Exceptions.pagination;

public class WrongSizeException extends RuntimeException{
    public WrongSizeException(String message) {
        super(message);
    }
}
