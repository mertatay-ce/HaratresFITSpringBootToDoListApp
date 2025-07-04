package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.user.*;
import com.haratres_fit.springboot_todolistapp.service.OtpService;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

// TODO: /app yerine /login endpoint e geç
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private PasswordEncoder passwordEncoder;




    @PostMapping("/verify-user")
    public ResponseEntity<ResultUserVerifyDto> verifyUser(@Valid @RequestBody VerifyUserDto verifyUserDto) {
        if (!userService.existsByEmail(verifyUserDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResultUserVerifyDto("","User is not verified."));
        }else{
            ResultUserVerifyDto resultUserVerifyDto = userService.verifyUser(verifyUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultUserVerifyDto);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResultForgotPasswordDto> forgotPassword(@Valid @RequestBody ForgotPasswordDto forgotPasswordDto){
        return ResponseEntity.ok(userService.forgotPassword(forgotPasswordDto));
    }


    @PostMapping("/reset-password")
    public ResponseEntity<ResultPasswordResetDto> resetPassword(@Valid @RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseEntity.ok(userService.resetPassword(resetPasswordDto));
    }
}
