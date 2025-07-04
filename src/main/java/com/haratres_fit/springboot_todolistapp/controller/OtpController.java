package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.user.GenerateOtpDto;
import com.haratres_fit.springboot_todolistapp.dto.user.ResultPasswordResetDto;
import com.haratres_fit.springboot_todolistapp.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpEmailService;


    public OtpController(OtpService otpEmailService) {
        this.otpEmailService = otpEmailService;
    }

    //TODO: OTP controller'a taşıyalım -> @RequestBody olarak alınmalı
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@Valid @RequestBody GenerateOtpDto generateOtpDto) {
        otpEmailService.generateOtp(generateOtpDto.getEmail(),generateOtpDto.getOtpType());
        return ResponseEntity.ok("OTP sent to your email");
    }
    //TODO: OTP controller'a taşıyalım -> @RequestBody olarak alınmalı
    @PostMapping("/validate-otp")
    public ResponseEntity<Boolean> validateOtp(@Valid @RequestBody GenerateOtpDto generateOtpDto, @RequestParam String otp) {
        boolean isValid = otpEmailService.validateOtp(generateOtpDto.getEmail(), otp,generateOtpDto.getOtpType());
        return ResponseEntity.ok(isValid);
    }
    //TODO: OTP controller'a taşıyalım -> @RequestBody olarak alınmalı
    @PostMapping("/delete-otp")
    public ResponseEntity<ResultPasswordResetDto> deleteOtp(@Valid @RequestBody GenerateOtpDto generateOtpDto, @RequestParam String otp) {
        boolean isValid = otpEmailService.validateOtp(generateOtpDto.getEmail(), otp,generateOtpDto.getOtpType());

        if (!isValid){
            ResultPasswordResetDto res = otpEmailService.deleteOtp(generateOtpDto);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResultPasswordResetDto("","Otp not found!"));
        }
    }
}
