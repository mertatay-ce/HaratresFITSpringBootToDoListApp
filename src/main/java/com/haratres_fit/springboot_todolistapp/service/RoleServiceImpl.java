package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.repository.RoleRepository;
import com.haratres_fit.springboot_todolistapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String role_name) {
        return roleRepository.findByName(role_name);
    }

    @Override
    public Optional<Role> addUser(String role_name, User user) {
        Optional<Role> tempRole = roleRepository.findByName(role_name);
        List<Role> userRoles = user.getRoles();
        if (userRoles == null){
            userRoles = new ArrayList<>();

        }

        userRoles.add(tempRole.get());

        user.setRoles(userRoles);
        return tempRole;
    }
}
