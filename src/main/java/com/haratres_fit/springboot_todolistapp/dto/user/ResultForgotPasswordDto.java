package com.haratres_fit.springboot_todolistapp.dto.user;

public class ResultForgotPasswordDto {
    private String success;
    private String error;

    public ResultForgotPasswordDto(String error, String success) {
        this.error = error;
        this.success = success;
    }

    public ResultForgotPasswordDto() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResultForgotPasswordDto{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
