package com.haratres_fit.springboot_todolistapp.exception.model.otp;

import com.haratres_fit.springboot_todolistapp.exception.model.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenOtpTypeUsageException extends ApplicationException {
    public ForbiddenOtpTypeUsageException(String message) {
        super(message);
    }
}
