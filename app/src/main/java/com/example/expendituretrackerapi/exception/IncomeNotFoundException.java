package com.example.expendituretrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class IncomeNotFoundException extends RuntimeException{

    private String message;

    public IncomeNotFoundException(String message) {
        this.message = message;
    }

    public IncomeNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public IncomeNotFoundException() {
        super();
    }
}
