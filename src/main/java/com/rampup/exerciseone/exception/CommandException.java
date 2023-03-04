package com.rampup.exerciseone.exception;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;

public class CommandException {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class CommandNotFound extends RuntimeException{
        public CommandNotFound(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static class IdCannotBeParsed extends RuntimeException{
        public IdCannotBeParsed(String message) {
            super(message);
        }
    }
}
