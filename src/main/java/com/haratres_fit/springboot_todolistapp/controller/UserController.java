package com.haratres_fit.springboot_todolistapp.controller;

import com.haratres_fit.springboot_todolistapp.dto.user.*;
import com.haratres_fit.springboot_todolistapp.service.OtpService;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final OtpService emailService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService, OtpService emailService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
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

    @DeleteMapping("/delete-user/{id}")// TODO: /users/{id}
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    //TODO: @PathVariable olarak id dön
    public ResponseEntity<ResultDeleteUserDto> deleteUser(@PathVariable int id) throws Exception {
        ResultDeleteUserDto res = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
    }
    //TODO: /register-user POST users endpointine bağlanacak
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResultRegisterUserDto> registerUser(@Valid @RequestBody RegisterPassiveUserDto registerUserDto) {
        if (userService.existsByUsername(registerUserDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResultRegisterUserDto("","This username is using by another user. Please create profile with another username."));
        }else{
            ResultRegisterUserDto resultRegisterUserDto = userService.signupUser(registerUserDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(resultRegisterUserDto);
        }
    }







}