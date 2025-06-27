package com.haratres_fit.springboot_todolistapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TodoListGlobalExceptionHandler {

    @ExceptionHandler({org.hibernate.exception.GenericJDBCException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(org.hibernate.exception.GenericJDBCException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
}
