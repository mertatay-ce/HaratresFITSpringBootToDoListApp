package com.haratres_fit.springboot_todolistapp.dto.todo;

import com.haratres_fit.springboot_todolistapp.model.entity.enums.TodoState;

public class TodosSortByStateDto {
    private TodoState todoState;
    private String sort_type;

    public TodosSortByStateDto() {
    }

    public TodosSortByStateDto(String sort_type, TodoState todoState) {
        this.sort_type = sort_type;
        this.todoState = todoState;
    }

    public TodoState getTodoState() {
        return todoState;
    }

    public void setTodoState(TodoState todoState) {
        this.todoState = todoState;
    }

    public String getSort_type() {
        return sort_type;
    }

    public void setSort_type(String sort_type) {
        this.sort_type = sort_type;
    }

    @Override
    public String toString() {
        return "TodoListSortByStateDto{" +
                "todoState=" + todoState +
                ", sort_type='" + sort_type + '\'' +
                '}';
    }
}
