package com.haratres_fit.springboot_todolistapp.dto.todo;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.TodoState;

import java.time.LocalDateTime;

public class UpdateTodoDto {
    private int id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private String image_url;
    private TodoState state;

    public UpdateTodoDto() {
    }

    public UpdateTodoDto(int id, String title, String description, LocalDateTime createdDate, String image_url, TodoState state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.image_url = image_url;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public TodoState getState() {
        return state;
    }

    public void setState(TodoState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "UpdateTodoDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", image_url='" + image_url + '\'' +
                ", state=" + state +
                '}';
    }
}
