package com.example.expendituretrackerapi.exception;

public class UserDetailNotFoundException extends RuntimeException {
    private String message;

    public UserDetailNotFoundException() {
    }

    public UserDetailNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
