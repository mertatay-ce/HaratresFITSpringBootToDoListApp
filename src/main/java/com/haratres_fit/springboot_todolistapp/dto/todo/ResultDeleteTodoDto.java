package com.haratres_fit.springboot_todolistapp.dto.todo;

public class ResultDeleteTodoDto {
    private String message;

    public ResultDeleteTodoDto(String message) {
        this.message = message;
    }

    public ResultDeleteTodoDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultDeleteTodoDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
