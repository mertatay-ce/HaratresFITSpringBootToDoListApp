package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.todo.*;
import com.haratres_fit.springboot_todolistapp.model.entity.TodoItem;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.TodoState;
import com.haratres_fit.springboot_todolistapp.repository.TodoRepository;
import com.haratres_fit.springboot_todolistapp.repository.specifications.todo.TodoSpecification;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;
    private final ModelMapper modelMapper;
    private final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);
    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper modelMapper) {
        this.todoRepository = todoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ResultTodoListDto> allTodos() {
        List<TodoItem> todoItems = todoRepository.findAll();
        return todoItems.stream().map(todo -> modelMapper.map(todo, ResultTodoListDto.class)).toList();

    }

    @Override
    public ResultTodoDto getTodoById(int id) {
        Optional<TodoItem> todoItem = todoRepository.findById(id);
        return modelMapper.map(todoItem.get(),ResultTodoDto.class);
    }

    @Override
    public ResultTodoDto createTodo(CreateTodoDto createTodoDto) {
        TodoItem new_todo_item = new TodoItem();
        new_todo_item.setCreatedDate(LocalDateTime.now());
        new_todo_item.setDescription(createTodoDto.getDescription());
        new_todo_item.setState(TodoState.CREATED);
        new_todo_item.setTitle(createTodoDto.getTitle());
        new_todo_item.setImage_url(createTodoDto.getImage_url());
        todoRepository.save(new_todo_item);
        return modelMapper.map(new_todo_item,ResultTodoDto.class);
    }

    @Override
    public ResultTodoDto updateTodo(UpdateTodoDto updateTodoDto) {
        Optional<TodoItem> updating_item = todoRepository.findById(updateTodoDto.getId());

        updating_item.ifPresentOrElse(item -> {
            item.setImage_url(updateTodoDto.getImage_url());
            item.setTitle(updateTodoDto.getTitle());
            item.setState(updateTodoDto.getState());
            item.setDescription(updateTodoDto.getDescription());
            item.setCreatedDate(LocalDateTime.now());
            todoRepository.save(item);
        },() -> {
            log.error("Güncellenemedi.");
        });
        Optional<TodoItem> updated_item = todoRepository.findById(updateTodoDto.getId());
        return modelMapper.map(updated_item.get(),ResultTodoDto.class);
    }

    @Override
    public String deleteTodoById(int id) {
        Optional<TodoItem> deleting_item = todoRepository.findById(id);

        deleting_item.ifPresentOrElse(System.out::println,()->{
            todoRepository.delete(deleting_item.get());
        });

        return "Todo item has been deleted";
    }

    @Override
    public List<ResultTodoListSortByDateAndFilterByStateOrTitleDto> listTodosSortByCreatedDateAndFilterByStateOrTitle(FilterRequestDto filterRequestDto) {
        TodoSpecification todoItemSpecification = new TodoSpecification(filterRequestDto.getState(),filterRequestDto.getTitle(),filterRequestDto.getCreated_date(),filterRequestDto.getSort());
        Pageable pageable = PageRequest.of(filterRequestDto.getPage_number(), filterRequestDto.getMax_pagesize());
        Page<TodoItem> items = todoRepository.findAll(todoItemSpecification,pageable);
        return items.stream().map(todoItem -> (modelMapper.map(todoItem, ResultTodoListSortByDateAndFilterByStateOrTitleDto.class))).collect(Collectors.toList());
    }



}
