package com.haratres_fit.springboot_todolistapp.exception.model.todo;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class InvalidTodoItemDueDateException extends ApplicationException {
    public InvalidTodoItemDueDateException(String message) {
        super("Your given due date format is wrong! Please use right format! Error: " + message);
    }
}
