package com.haratres_fit.springboot_todolistapp.exception;

import com.haratres_fit.springboot_todolistapp.exception.model.ErrorResponse;
import com.haratres_fit.springboot_todolistapp.exception.model.otp.ForbiddenOtpTypeUsageException;
import com.haratres_fit.springboot_todolistapp.exception.model.otp.OtpNotCreatedException;
import com.haratres_fit.springboot_todolistapp.exception.model.otp.OtpNotFoundException;
import com.haratres_fit.springboot_todolistapp.exception.model.role.DuplicateRoleException;
import com.haratres_fit.springboot_todolistapp.exception.model.role.RoleAssignmentDeniedException;
import com.haratres_fit.springboot_todolistapp.exception.model.role.RoleNotFoundException;
import com.haratres_fit.springboot_todolistapp.exception.model.todo.InvalidTodoItemDueDateException;
import com.haratres_fit.springboot_todolistapp.exception.model.todo.TodoItemAlreadyCompletedException;
import com.haratres_fit.springboot_todolistapp.exception.model.todo.TodoItemNotFoundException;
import com.haratres_fit.springboot_todolistapp.exception.model.user.*;
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
import java.util.Arrays;

//TODO: Multi Custom Exception Handlers olarak konu araştırması
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

    @ExceptionHandler({ DuplicateRoleException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDuplicateRoleException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.CONFLICT.toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    @ExceptionHandler({ RoleAssignmentDeniedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleRoleAssignmentDeniedException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),LocalDateTime.now(), Arrays.toString(ex.getStackTrace()),HttpStatus.UNAUTHORIZED.toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    @ExceptionHandler({ RoleNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler({ InvalidTodoItemDueDateException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInvalidTodoItemDueDateException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.BAD_REQUEST.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler({ TodoItemAlreadyCompletedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleTodoItemAlreadyCompletedException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.CONFLICT.toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    @ExceptionHandler({ TodoItemNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleTodoItemNotFoundException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler({ DuplicateUserEmailException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDuplicateUserEmailException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.CONFLICT.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.CONFLICT.toString());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
    @ExceptionHandler({ InactiveUserException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInactiveUserException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.FORBIDDEN.toString());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    @ExceptionHandler({ InvalidPasswordException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleInvalidPasswordException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.BAD_REQUEST.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    @ExceptionHandler({ UserNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler({ UserUnauthorizedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUserUnauthorizedException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.UNAUTHORIZED.toString());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
    @ExceptionHandler({ OtpNotCreatedException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOtpNotCreatedException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.BAD_REQUEST.toString());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler({ OtpNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOtpNotFoundException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler({ ForbiddenOtpTypeUsageException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleForbiddenOtpTypeUsageException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.FORBIDDEN.toString());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
    }
    @ExceptionHandler({ EmailNotFoundException.class })
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEmailNotFoundException(Exception ex) {

        ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),LocalDateTime.now(),ex.getStackTrace().toString(),HttpStatus.NOT_FOUND.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

}
