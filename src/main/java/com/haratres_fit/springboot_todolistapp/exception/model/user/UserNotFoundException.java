package com.haratres_fit.springboot_todolistapp.exception.model.user;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ApplicationException {
    public UserNotFoundException(int id) {
      super("User is not found the given id : " + id);
    }
}
