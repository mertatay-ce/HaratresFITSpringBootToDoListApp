package com.haratres_fit.springboot_todolistapp.exception.model.todo;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TodoItemAlreadyCompletedException extends ApplicationException {
    public TodoItemAlreadyCompletedException(String message) {
        super("Todo item already has been completed! Error:" + message);
    }
}
