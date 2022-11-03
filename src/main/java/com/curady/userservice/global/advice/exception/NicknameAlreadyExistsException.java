package com.curady.userservice.global.advice.exception;

public class NicknameAlreadyExistsException extends RuntimeException{
    public NicknameAlreadyExistsException() {
    }

    public NicknameAlreadyExistsException(String message) {
        super(message);
    }

    public NicknameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
