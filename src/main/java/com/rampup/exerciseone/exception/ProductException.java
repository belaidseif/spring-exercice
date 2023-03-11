package com.rampup.exerciseone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ProductException {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ProductNotFound extends RuntimeException{
        public ProductNotFound(String message) {
            super(message);
        }
    }
}
