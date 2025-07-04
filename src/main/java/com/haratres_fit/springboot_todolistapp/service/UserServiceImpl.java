package com.haratres_fit.springboot_todolistapp.service;

import com.haratres_fit.springboot_todolistapp.dto.user.*;
import com.haratres_fit.springboot_todolistapp.exception.model.user.InactiveUserException;
import com.haratres_fit.springboot_todolistapp.exception.model.user.InvalidPasswordException;
import com.haratres_fit.springboot_todolistapp.exception.model.user.UserNotFoundException;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.otp.OtpType;
import com.haratres_fit.springboot_todolistapp.model.entity.security.OtpToken;
import com.haratres_fit.springboot_todolistapp.repository.RoleRepository;
import com.haratres_fit.springboot_todolistapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    @Lazy
    private OtpService otpService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

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
    @Override
    public ResultRegisterUserDto signupUser(RegisterPassiveUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");

        if (optionalRole.isEmpty()) {
            return new ResultRegisterUserDto("","Role is not found!");
        }

        var user = new User();
        user.setLastName(input.getLastName());
        user.setFirstName(input.getFirstName());
        user.setGender(input.getGender());
        user.setBirthDate(input.getBirthDate());
        user.setTelephone_number(input.getTelephone_number());
        user.setActive(false);
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setEmail(input.getEmail());
        roleService.addUser("ROLE_USER",user);
        userRepository.save(user);
        otpService.generateOtp(input.getEmail(),input.getOtpType());
        return new ResultRegisterUserDto("User is created and we sended the OTP for verify your user.","");
    }

    @Override
    public ResultUserVerifyDto verifyUser(VerifyUserDto verifyUserDto){
        User user = userRepository.findByEmail(verifyUserDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email address is not found"));

        boolean isValid = otpService.validateOtp(verifyUserDto.getEmail(),verifyUserDto.getOtp(),verifyUserDto.getOtpType());
        if (!isValid){
            throw new InactiveUserException("Did not verified user from the service");
        }else{
            user.setActive(true);
            saveUser(user);
            return new ResultUserVerifyDto("User is verified.","");
        }

    }
    @Override
    public void updatePassword(String username, String currentPassword, String newPassword) throws Exception {
        // Find the user by username or throw an exception if not found
        User user = userRepository.findByUserName(username);
        if (user == null){
            throw new UserNotFoundException(0);
        }

        // Check if the current password matches
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidPasswordException("Current password is incorrect");
        }

        // Encode and set the new password, then save the user
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
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
    @Override
    public boolean existsByUsername(String username) {
        User tempUser = userRepository.findByUserName(username);
        if (tempUser != null){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean existsByEmail(String email) {
        User tempUser = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email is not found!"));
        if (tempUser != null){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    //TODO: DeleteUserDto u kaldırıp id olarak parametre al
    @Override
    public ResultDeleteUserDto deleteUser(int id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        //rolleri listele
        List<Role> userRoles = user.getRoles();

        user.getRoles().removeAll(userRoles);
        //user dan rolleri kaldır
        try{
            userRepository.delete(user);
        }catch (Exception ex){
            throw new Exception(ex);
        }

        return new ResultDeleteUserDto("Kullanıcı silindi.","");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ResultForgotPasswordDto forgotPassword(ForgotPasswordDto forgotPasswordDto) {

        if (forgotPasswordDto.getOtpType().equals(OtpType.REGISTER_OTP_TYPE)){
            return new ResultForgotPasswordDto("Register işlemine tabi OTP kodu kullanılamaz.","");
        }else{
            User user1 = findByEmail(forgotPasswordDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email kayıtlı değil"));
            User user2 = findByUserName(forgotPasswordDto.getUsername());

            if (!(user1.getUsername().equals(user2.getUsername()) && user1.getEmail().equals(user2.getEmail()))){
                return new ResultForgotPasswordDto("User eşleşmesi sağlanamadı!", "");
            }else{
                String s = otpService.generateOtp(user1.getEmail(),forgotPasswordDto.getOtpType());
                return new ResultForgotPasswordDto("","Otp is sent -> " + s);
            }
        }


    }

    @Override
    public ResultPasswordResetDto resetPassword(ResetPasswordDto resetPasswordDto) {
        User user = findByEmail(resetPasswordDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email kayıtlı değil"));

        if (user == null){
            return new ResultPasswordResetDto("", "User bulunamadı!");
        }else{
            // TODO: findOTPCodeFromUserByEmail metodu uygun servis metodu olarak gerçekleştir
            OtpToken otpEntity = otpService.findOTPCodeFromUserByEmail(resetPasswordDto.getEmail());
            if (!otpEntity.getToken().equals(resetPasswordDto.getOtp())){
                return new ResultPasswordResetDto("","OTP doğrulanamadı!");
            }else{
                String hashedPassword = passwordEncoder.encode(resetPasswordDto.getNewPassword());
                user.setPassword(hashedPassword);

                saveUser(user);
                return new ResultPasswordResetDto("Güncelleme başarılı!", "");
            }
        }


    }


}
