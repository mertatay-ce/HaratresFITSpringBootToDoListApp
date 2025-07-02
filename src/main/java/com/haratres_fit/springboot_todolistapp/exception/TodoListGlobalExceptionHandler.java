package com.haratres_fit.springboot_todolistapp.exception;

import com.haratres_fit.springboot_todolistapp.exception.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class TodoListGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TodoListGlobalExceptionHandler.class);


    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleAuthenticationException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                "Authentication failed at controller advice",LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.UNAUTHORIZED.toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }

}
