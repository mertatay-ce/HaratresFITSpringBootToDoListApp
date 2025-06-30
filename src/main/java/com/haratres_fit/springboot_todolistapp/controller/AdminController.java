package com.haratres_fit.springboot_todolistapp.controller;
import com.haratres_fit.springboot_todolistapp.dto.userdto.RegisterUserDto;
import com.haratres_fit.springboot_todolistapp.dto.userdto.ResultUserDto;
import com.haratres_fit.springboot_todolistapp.dto.userdto.UserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResultUserDto> createAdministrator(@Valid @RequestBody RegisterUserDto registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);
        ResultUserDto res = userService.getUserInfo(createdAdmin);
        return ResponseEntity.ok(res);
    }
}

