package com.haratres_fit.springboot_todolistapp.dto;

import com.haratres_fit.springboot_todolistapp.model.entity.User;

public interface UserDto {
    User findByUserName(String username);
}
