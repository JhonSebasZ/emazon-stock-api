package com.emazon.emazon_stock_api.domain.exceptions;

public class InvalidCategoryDescriptionException extends  RuntimeException{
    public InvalidCategoryDescriptionException(String message){
        super(message);
    }
}
