package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.RoleDto;
import com.haratres_fit.springboot_todolistapp.dto.UserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserDto userDao;
    private RoleDto roleDao;

    @Autowired
    public UserServiceImpl(UserDto userDao, RoleDto roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));

    }
    private Set<GrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole()))
                .collect(Collectors.toSet());
    }
}
