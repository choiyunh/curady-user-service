package com.curady.userservice.advice.exception;

public class TendencyNotFoundException extends RuntimeException {
    public TendencyNotFoundException() {
    }

    public TendencyNotFoundException(String message) {
        super(message);
    }

    public TendencyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}