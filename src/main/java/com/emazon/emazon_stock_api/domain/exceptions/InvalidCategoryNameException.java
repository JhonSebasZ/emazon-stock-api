package com.emazon.emazon_stock_api.domain.exceptions;

public class InvalidCategoryNameException extends RuntimeException{
    public InvalidCategoryNameException(String message){
        super(message);
    }
}
