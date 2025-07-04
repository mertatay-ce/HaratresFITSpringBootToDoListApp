package com.haratres_fit.springboot_todolistapp.dto.user;

public class DeleteUserDto {
    private int id;

    public DeleteUserDto(int id) {
        this.id = id;
    }

    public DeleteUserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
