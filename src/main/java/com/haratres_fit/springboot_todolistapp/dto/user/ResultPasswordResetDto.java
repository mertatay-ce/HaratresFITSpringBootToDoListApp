package com.haratres_fit.springboot_todolistapp.dto.user;

public class ResultPasswordResetDto {
    private String message;
    private String error;

    public ResultPasswordResetDto(String message) {
        super();
        this.message = message;
    }

    public ResultPasswordResetDto(String message, String error) {
        super();
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResultPasswordResetDto{" +
                "message='" + message + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
