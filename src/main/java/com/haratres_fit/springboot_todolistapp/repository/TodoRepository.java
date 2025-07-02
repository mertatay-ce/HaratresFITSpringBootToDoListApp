package com.haratres_fit.springboot_todolistapp.repository;

import com.haratres_fit.springboot_todolistapp.model.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository extends JpaRepository<TodoItem,Integer> , JpaSpecificationExecutor<TodoItem> {

}
