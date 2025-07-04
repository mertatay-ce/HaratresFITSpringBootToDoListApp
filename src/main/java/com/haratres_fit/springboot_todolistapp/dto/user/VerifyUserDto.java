package com.haratres_fit.springboot_todolistapp.dto.user;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;

public class VerifyUserDto {
    private String otp;
    private String email;
    private OtpType otpType;

    public VerifyUserDto(String otp, String email, OtpType otpType) {
        this.otp = otp;
        this.email = email;
        this.otpType = otpType;
    }

    public VerifyUserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "VerifyUserDto{" +
                "otpToken=" + otp +
                ", email='" + email + '\'' +
                ", otpType='" + otpType + '\'' +
                '}';
    }

    public OtpType getOtpType() {
        return otpType;
    }

    public void setOtpType(OtpType otpType) {
        this.otpType = otpType;
    }
}
