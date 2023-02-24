package com.example.expendituretrackerapi.exception;

public class ExpenditureNotFoundException extends RuntimeException{

    private String message;

    public ExpenditureNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public ExpenditureNotFoundException() {
    }
}
