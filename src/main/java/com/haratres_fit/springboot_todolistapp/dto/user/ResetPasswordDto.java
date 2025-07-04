package com.haratres_fit.springboot_todolistapp.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ResetPasswordDto {

    @NotNull
    @Email
    private String email;
    //TODO : old_password -> oldPassword ve getter ile setterlar
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String confirmPassword;
    @NotNull
    private String otp;

    public ResetPasswordDto(String email, String old_password, String new_password, String confirm_password, String otp) {
        this.email = email;
        this.oldPassword = old_password;
        this.newPassword = new_password;
        this.confirmPassword = confirm_password;
        this.otp = otp;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ResetPasswordDto{" +
                "email='" + email + '\'' +
                ", old_password='" + oldPassword + '\'' +
                ", new_password='" + newPassword + '\'' +
                ", confirm_password='" + confirmPassword + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }
}
