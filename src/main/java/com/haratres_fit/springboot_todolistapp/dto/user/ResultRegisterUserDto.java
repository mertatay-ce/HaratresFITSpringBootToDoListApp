package com.haratres_fit.springboot_todolistapp.dto.user;

public class ResultRegisterUserDto {
    private String success;
    private String error;

    public ResultRegisterUserDto(String success, String error) {
        this.success = success;
        this.error = error;
    }

    public ResultRegisterUserDto() {
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
        return "ResultRegisterUserDto{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
