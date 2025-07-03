package com.haratres_fit.springboot_todolistapp.dto.todo;

import java.time.LocalDate;

public class TodosSortByDateDto {
    private LocalDate date;
    private String sort_type;

    public TodosSortByDateDto() {

    }

    public TodosSortByDateDto(LocalDate date, String sortType) {
        this.date = date;
        sort_type = sortType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSort_type() {
        return sort_type;
    }

    public void setSort_type(String sort_type) {
        this.sort_type = sort_type;
    }

    @Override
    public String toString() {
        return "TodosSortByDateDto{" +
                "date=" + date +
                '}';
    }
}
