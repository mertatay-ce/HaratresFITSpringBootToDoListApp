package com.haratres_fit.springboot_todolistapp.bootstrap;

import com.haratres_fit.springboot_todolistapp.dto.userdto.RegisterUserDto;
import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.model.entity.User;
import com.haratres_fit.springboot_todolistapp.model.entity.enums.Gender;
import com.haratres_fit.springboot_todolistapp.repository.RoleRepository;
import com.haratres_fit.springboot_todolistapp.repository.UserRepository;
import com.haratres_fit.springboot_todolistapp.service.RoleService;
import com.haratres_fit.springboot_todolistapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository, RoleService roleService, UserService userService
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createAdministrator();
    }

    private void createAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFirstName("Mert");
        userDto.setLastName("Atay");
        userDto.setGender(Gender.MALE);
        userDto.setBirthDate(LocalDate.parse("2000-05-06"));
        userDto.setTelephone_number("05467289221");
        userDto.setActive(true);
        userDto.setUsername("mertatay");
        userDto.setPassword("test123");
        userDto.setEmail("mertatay41@gmail.com");

        System.out.println(userDto);

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_ADMIN");
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        optionalUser.ifPresentOrElse(System.out::println, () -> {
            User adminToCreate = new User();

            adminToCreate.setFirstName("Mert");
            adminToCreate.setLastName("Atay");
            adminToCreate.setGender(Gender.MALE);
            adminToCreate.setBirthDate(LocalDate.parse("2000-05-06"));
            adminToCreate.setTelephone_number("05467289221");
            adminToCreate.setActive(true);
            adminToCreate.setUsername("mertatay");
            adminToCreate.setPassword("test123");
            adminToCreate.setEmail("mertatay41@gmail.com");

            roleService.addUser(optionalRole.get().getName(),adminToCreate);
            System.out.println(adminToCreate);
            System.out.println(adminToCreate.getRoles());
            userRepository.save(adminToCreate);

        });




    }
}
