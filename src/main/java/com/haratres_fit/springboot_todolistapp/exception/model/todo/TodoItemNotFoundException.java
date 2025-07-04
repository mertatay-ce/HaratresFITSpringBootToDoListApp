package com.haratres_fit.springboot_todolistapp.exception.model.todo;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TodoItemNotFoundException extends ApplicationException {
    public TodoItemNotFoundException(String message) {
        super("Todo item not found. Id:" + message);
    }
}
