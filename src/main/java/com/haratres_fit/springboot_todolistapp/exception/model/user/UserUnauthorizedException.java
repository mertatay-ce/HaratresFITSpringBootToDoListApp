package com.haratres_fit.springboot_todolistapp.exception.model.user;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UserUnauthorizedException extends ApplicationException {
    public UserUnauthorizedException(String username) {
        super("Unauthorized a process tried for user : " + username);
    }
}
