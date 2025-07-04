package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.user.*;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    User addRole(Role tempRole, String username);
    List<UserDto> allUsers();
    User createAdministrator(RegisterUserDto input);
    ResultRegisterUserDto signupUser(RegisterPassiveUserDto input);
    ResultUserVerifyDto verifyUser(VerifyUserDto verifyUserDto);
    ResultUserDto getUserInfo(User user);
    ResultAuthUserDto getAuthenticatedUser();
    void updatePassword(String username, String currentPassword, String newPassword) throws Exception;
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void saveUser(User user);
    ResultDeleteUserDto deleteUser(int id) throws Exception;
    Optional<User> findByEmail(String email);
    ResultForgotPasswordDto forgotPassword(ForgotPasswordDto forgotPasswordDto);
    ResultPasswordResetDto resetPassword(ResetPasswordDto resetPasswordDto);
}
