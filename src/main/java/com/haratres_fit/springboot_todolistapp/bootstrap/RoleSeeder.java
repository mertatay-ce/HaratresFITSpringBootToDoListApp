package com.haratres_fit.springboot_todolistapp.bootstrap;

import com.haratres_fit.springboot_todolistapp.model.entity.Role;
import com.haratres_fit.springboot_todolistapp.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private static final Logger log = LoggerFactory.getLogger(RoleSeeder.class);

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.loadRoles();
    }

    private void loadRoles(){
        List<Role> roles = new ArrayList<>();

        roles.add(new Role("ROLE_ADMIN"));
        roles.add(new Role("ROLE_USER"));

        for (Role tempRole : roles){
            Optional<Role> optionalRole = roleRepository.findByName(tempRole.getName());

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(tempRole.getName());

                roleRepository.save(roleToCreate);
                log.info("Loaded roles from RoleSeeder.java: {}", roleToCreate.getName().toString());
            });
        }
    }
}
