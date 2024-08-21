package com.emazon.emazon_stock_api.domain.exceptions;

public class BrandExceptions {

    public static class ExistingBrandException extends RuntimeException {
        public ExistingBrandException(String message) {
            super(message);
        }
    }

    public static class InvalidBrandNameException extends RuntimeException {
        public InvalidBrandNameException(String message) {
            super(message);
        }
    }

    public static class InvalidBrandDescriptionException extends RuntimeException {
        public InvalidBrandDescriptionException(String message) {
            super(message);
        }
    }
}
