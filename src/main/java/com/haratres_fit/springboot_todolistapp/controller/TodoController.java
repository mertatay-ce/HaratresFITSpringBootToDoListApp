package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.todo.*;
import com.haratres_fit.springboot_todolistapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResultTodoDto> todosList(@PathVariable int id){
        ResultTodoDto todoDto = todoService.getTodoById(id);
        if (todoDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(todoDto);
        }

    }

    @PostMapping("/")
    public ResponseEntity<ResultTodoDto> createTodo(@RequestBody CreateTodoDto createTodoDto){
        ResultTodoDto resultTodoDto = todoService.createTodo(createTodoDto);
        if (resultTodoDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(resultTodoDto);
        }
    }

    @PutMapping("/")
    public ResponseEntity<ResultTodoDto> updateTodo(@RequestBody UpdateTodoDto updateTodoDto){
        ResultTodoDto resultTodoDto = todoService.updateTodo(updateTodoDto);
        if (resultTodoDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(resultTodoDto);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable int id){
        String response = todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }



    @GetMapping("/list-by-filter")
    public ResponseEntity<List<ResultTodoListSortByDateAndFilterByStateOrTitleDto>> listTodosByFilter(@Valid @ModelAttribute(name = "filterRequestDto") FilterRequestDto filterRequestDto){
        List<ResultTodoListSortByDateAndFilterByStateOrTitleDto> todos = todoService.listTodosSortByCreatedDateAndFilterByStateOrTitle(filterRequestDto);
        if (todos == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok(todos);
        }

    }

}
