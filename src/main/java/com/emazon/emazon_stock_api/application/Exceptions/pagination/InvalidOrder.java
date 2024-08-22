package com.emazon.emazon_stock_api.application.Exceptions.pagination;

public class InvalidOrder extends RuntimeException{
    public InvalidOrder(String message) {
        super(message);
    }
}
