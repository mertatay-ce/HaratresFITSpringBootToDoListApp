package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String role_name);

    Optional<Role> addUser(String role_name, User user);
}
