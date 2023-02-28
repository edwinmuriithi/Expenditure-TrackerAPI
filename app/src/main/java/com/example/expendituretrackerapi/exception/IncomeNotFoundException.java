package com.example.expendituretrackerapi.exception;


public class IncomeNotFoundException extends RuntimeException{

    private String message;

    public IncomeNotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public IncomeNotFoundException() {
    }
}
