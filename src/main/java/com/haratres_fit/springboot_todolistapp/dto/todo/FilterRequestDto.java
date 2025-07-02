package com.haratres_fit.springboot_todolistapp.dto.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class FilterRequestDto {
    @Nullable
    private String title;

    @Nullable
    private String state;

    @NotNull(message = "created_date is not null.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime created_date;
    @NotNull(message = "sort is not null.")
    private Sort.Direction sort;

    @NotNull(message = "page_number is not null.")
    private int page_number;
    @NotNull(message = "max_pagesize is not null.")
    private int max_pagesize;



    public FilterRequestDto(String title, String state, LocalDateTime created_date, Sort.Direction sort, int pageNumber, int maxPagesize) {
        this.title = title;
        this.state = state;
        this.created_date = created_date;
        this.sort = sort;
        page_number = pageNumber;
        max_pagesize = maxPagesize;
    }

    public FilterRequestDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public Sort.Direction getSort() {
        return sort;
    }

    public void setSort(Sort.Direction sort) {
        this.sort = sort;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public int getMax_pagesize() {
        return max_pagesize;
    }

    public void setMax_pagesize(int max_pagesize) {
        this.max_pagesize = max_pagesize;
    }

    @Override
    public String toString() {
        return "FilterRequestDto{" +
                "title='" + title + '\'' +
                ", state=" + state +
                ", created_date=" + created_date +
                ", sort=" + sort +
                ", page_number=" + page_number +
                ", max_pagesize=" + max_pagesize +
                '}';
    }
}
