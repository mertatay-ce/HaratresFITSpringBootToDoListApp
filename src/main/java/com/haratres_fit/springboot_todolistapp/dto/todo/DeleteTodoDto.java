package com.haratres_fit.springboot_todolistapp.dto.todo;

public class DeleteTodoDto {
    private int id;

    public DeleteTodoDto() {
    }

    public DeleteTodoDto(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
