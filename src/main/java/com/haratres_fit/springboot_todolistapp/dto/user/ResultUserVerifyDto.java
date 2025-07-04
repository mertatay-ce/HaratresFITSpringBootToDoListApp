package com.haratres_fit.springboot_todolistapp.dto.user;

public class ResultUserVerifyDto {
    private String success;
    private String error;

    public ResultUserVerifyDto(String success, String error) {
        this.success = success;
        this.error = error;
    }

    public ResultUserVerifyDto() {
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
        return "ResultUserVerifyDto{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
