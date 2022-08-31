package com.scheduleproject.exceptions;

public class NoEntityByIdException extends RuntimeException {
    public NoEntityByIdException(String message) {
        super(message);
    }
}
