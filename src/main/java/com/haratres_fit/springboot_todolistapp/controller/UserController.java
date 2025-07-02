package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.user.ResultAuthUserDto;
import com.haratres_fit.springboot_todolistapp.dto.user.UserDto;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated() && hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ResultAuthUserDto> authenticatedUser() {
        ResultAuthUserDto currentUserDto = userService.getAuthenticatedUser();
        return ResponseEntity.ok(currentUserDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDto>> allUsers() {
        List <UserDto> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}