package com.haratres_fit.springboot_todolistapp.dto.user;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;

public class ForgotPasswordDto {
    private String email;
    private String username;
    private OtpType otpType;

    public ForgotPasswordDto(OtpType otpType, String email, String username) {
        this.otpType = otpType;
        this.email = email;
        this.username = username;
    }

    public ForgotPasswordDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OtpType getOtpType() {
        return otpType;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }

    @Override
    public String toString() {
        return "ForgotPasswordDto{" +
                "email='" + email + '\'' +
                ", otpType=" + otpType +
                ", username=" + username +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
