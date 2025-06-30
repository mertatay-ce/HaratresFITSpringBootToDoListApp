package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.userdto.RegisterUserDto;
import com.haratres_fit.springboot_todolistapp.dto.userdto.ResultAuthUserDto;
import com.haratres_fit.springboot_todolistapp.dto.userdto.ResultUserDto;
import com.haratres_fit.springboot_todolistapp.dto.userdto.UserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.repository.RoleRepository;
import com.haratres_fit.springboot_todolistapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RoleService roleService;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    public UserServiceImpl() {
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User addRole(Role tempRole, String username) {
        User tempUser = userRepository.findByUserName(username);
        List<Role> userRoles = tempUser.getRoles();
        if (userRoles == null) {
            userRoles = new ArrayList<>();
        }

        userRoles.add(tempRole);
        return tempUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));

    }
    private Set<GrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
    }

    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        for (UserDto dto: dtos){
            System.out.println(dto.toString());
        }
        return dtos;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setLastName(input.getLastName());
        user.setFirstName(input.getFirstName());
        user.setGender(input.getGender());
        user.setBirthDate(input.getBirthDate());
        user.setTelephone_number(input.getTelephone_number());
        user.setActive(true);
        user.setUsername(input.getUsername());
        user.setPassword(input.getPassword());
        user.setEmail(input.getEmail());
        roleService.addUser("ROLE_ADMIN",user);





        return userRepository.save(user);
    }
    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setLastName(input.getLastName());
        user.setFirstName(input.getFirstName());
        user.setGender(input.getGender());
        user.setBirthDate(input.getBirthDate());
        user.setTelephone_number(input.getTelephone_number());
        user.setActive(true);
        user.setUsername(input.getUsername());
        user.setPassword(input.getPassword());
        user.setEmail(input.getEmail());
        roleService.addUser("ROLE_USER",user);


        return userRepository.save(user);
    }

    @Override
    public ResultUserDto getUserInfo(User user) {
        User tempUser = userRepository.findByUserName(user.getUsername());
        return modelMapper.map(tempUser, ResultUserDto.class);
    }

    @Override
    public ResultAuthUserDto getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        org.springframework.security.core.userdetails.User currentUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        return modelMapper.map(currentUser,ResultAuthUserDto.class);
    }


}
