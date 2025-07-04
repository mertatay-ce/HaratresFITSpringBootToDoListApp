package com.haratres_fit.springboot_todolistapp.dto.user;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class GenerateOtpDto {

    @NotNull
    @Email
    private String email;

    private OtpType otpType;

    public GenerateOtpDto(String email, OtpType otpType) {
        this.email = email;
        this.otpType = otpType;
    }

    public GenerateOtpDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PasswordResetDto{" +
                "email='" + email + '\'' +
                '}';
    }

    public OtpType getOtpType() {
        return otpType;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }
}
