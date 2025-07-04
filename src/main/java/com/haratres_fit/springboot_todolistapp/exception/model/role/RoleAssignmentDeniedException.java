package com.haratres_fit.springboot_todolistapp.exception.model.role;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class RoleAssignmentDeniedException extends ApplicationException {
    public RoleAssignmentDeniedException(String message) {
        super("Access denied for role assignment for this user: " +message);
    }
}
