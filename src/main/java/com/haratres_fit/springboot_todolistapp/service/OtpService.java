package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.user.GenerateOtpDto;
import com.haratres_fit.springboot_todolistapp.dto.user.ResultPasswordResetDto;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import com.haratres_fit.springboot_todolistapp.model.entity.security.OtpToken;

//TODO: sendEmail metodu ayrı bir EmailService sınıfına taşıyalım ve servis adını guncelleyelım
public interface OtpService {
    String generateOtp(String email, OtpType otpType);
    //TODO: sendEmail -> sendOtp()
    void sendOtp(String email, String otp, OtpType otp_type);
    boolean validateOtp(String email,String otp,OtpType otpType);
    ResultPasswordResetDto deleteOtp(GenerateOtpDto generateOtpDto);
    OtpToken findOTPCodeFromUserByEmail(String email);
}
