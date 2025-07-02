package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.todo.*;

import java.util.List;

public interface TodoService {
    List<ResultTodoListDto> allTodos();
    ResultTodoDto getTodoById(int id);
    ResultTodoDto createTodo(CreateTodoDto createTodoDto);
    ResultTodoDto updateTodo(UpdateTodoDto updateTodoDto);
    String deleteTodoById(int id);
    List<ResultTodoListSortByDateAndFilterByStateOrTitleDto> listTodosSortByCreatedDateAndFilterByStateOrTitle(FilterRequestDto filterRequestDto);
}
