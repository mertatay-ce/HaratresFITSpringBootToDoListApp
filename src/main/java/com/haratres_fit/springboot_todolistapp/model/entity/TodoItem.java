package com.haratres_fit.springboot_todolistapp.model.entity;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.TodoState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "todo")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    @NotNull(message = "Todo item id is not be empty.")
    private int id;

    @Column(name="title", nullable = false, length = 50)
    @NotNull(message = "Todo item message is not be empty.")
    private String title;

    @Column(name="description",nullable = false,length = 1000)
    @NotNull(message = "Todo item description is not be empty.")
    private String description;

    @Column(name = "created_date",nullable = false)
    @NotNull(message = "Todo item's created date is not be empty.")
    private Date createdDate;

    @Column(name = "image_url", nullable = true,length = 300)
    @NotNull(message = "Todo item image url is entering nullable.")
    private String image_url;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    @NotNull(message = "Todo item's state is not stateless. Please check the state of todo items's task.")
    private TodoState state;

    public TodoItem() {
    }

    public TodoItem(String title, String description, Date createdDate, String image_url, TodoState state) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
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
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", image_url='" + image_url + '\'' +
                ", state=" + state.name() +
                '}';
    }
}
