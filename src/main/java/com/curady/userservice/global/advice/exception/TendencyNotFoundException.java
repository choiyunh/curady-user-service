package com.curady.userservice.global.advice.exception;

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