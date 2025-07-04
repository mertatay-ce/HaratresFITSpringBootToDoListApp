package com.haratres_fit.springboot_todolistapp.exception.model;

public abstract class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}
