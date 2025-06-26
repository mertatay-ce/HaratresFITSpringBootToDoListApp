package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);

}
