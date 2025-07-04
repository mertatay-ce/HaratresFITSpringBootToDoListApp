package com.haratres_fit.springboot_todolistapp.exception.model.user;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InactiveUserException extends ApplicationException {
    public InactiveUserException(String message) {
        super("User not active and doing activity is prohibited for user");
    }
}
