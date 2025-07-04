package com.haratres_fit.springboot_todolistapp.exception.model.user;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends ApplicationException {
    public InvalidPasswordException(String message) {
        super("Didn't match your passwords!");
    }
}
