package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.userdto.UserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.security.ToDoListAuthenticationProvider;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    private final ToDoListAuthenticationProvider toDoListAuthenticationProvider;

    public UserController(UserService userService, ToDoListAuthenticationProvider toDoListAuthenticationProvider) {
        this.userService = userService;
        this.toDoListAuthenticationProvider = toDoListAuthenticationProvider;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<org.springframework.security.core.userdetails.User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<UserDto>> allUsers() {
        List <UserDto> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}