package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.user.RegisterUserDto;
import com.haratres_fit.springboot_todolistapp.dto.user.ResultAuthUserDto;
import com.haratres_fit.springboot_todolistapp.dto.user.ResultUserDto;
import com.haratres_fit.springboot_todolistapp.dto.user.UserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    User addRole(Role tempRole, String username);
    List<UserDto> allUsers();
    User createAdministrator(RegisterUserDto input);
    User signup(RegisterUserDto input);
    ResultUserDto getUserInfo(User user);
    ResultAuthUserDto getAuthenticatedUser();
}
