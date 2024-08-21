package com.emazon.emazon_stock_api.domain.exceptions.category;

public class InvalidCategoryNameException extends RuntimeException{
    public InvalidCategoryNameException(String message){
        super(message);
    }
}
