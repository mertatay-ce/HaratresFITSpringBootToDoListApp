package com.haratres_fit.springboot_todolistapp.exception.model.role;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends ApplicationException {
    public RoleNotFoundException(String message) {
        super("Role is not found with given id: " + message);
    }
}
