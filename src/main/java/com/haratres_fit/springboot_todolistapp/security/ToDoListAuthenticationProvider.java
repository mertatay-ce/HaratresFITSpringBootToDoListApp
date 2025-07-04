package com.haratres_fit.springboot_todolistapp.security;

import com.haratres_fit.springboot_todolistapp.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ToDoListAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;


    public ToDoListAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // Perform your custom authentication logic here
        // Retrieve user details from userDetailsService and validate the credentials
        // You can throw AuthenticationException if authentication fails
        // Example: retrieving user details by username from UserDetailsService

        if(username == null){
            throw new NullPointerException("User not found!");
        }

        if(password == null) {
            throw new NullPointerException("Password not found!!");
        }

        UserDetails userDetails = userService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }else{
            // Example: validating credentials
            if (!password.equals(userDetails.getPassword())) {
                throw new AuthenticationException("Invalid credentials") {};
            }
        }

        // Create a fully authenticated Authentication object
        Authentication authenticated = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities());

        if(authenticated.isAuthenticated()){
            System.out.println("Authentication success!!");
        }else{
            throw new AuthenticationException("Authentication failed!!!") {};
        }
        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


}
