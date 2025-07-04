package com.haratres_fit.springboot_todolistapp.exception.model.role;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateRoleException extends ApplicationException {
    public DuplicateRoleException(String message) {
        super("Duplicated role error:" + message);
    }
}
