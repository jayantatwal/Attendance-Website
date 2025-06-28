package com.jayant.AttendanceWebsite.service;

import com.jayant.AttendanceWebsite.model.User;
import com.jayant.AttendanceWebsite.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;


//It loads a user from your database based on the username during login.
//It wraps your User entity as a UserDetails object required by Spring Security.
//It assigns a role like ROLE_ADMIN, ROLE_TEACHER, etc. (assuming your DB stores just ADMIN, TEACHER, etc.)
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);  // <-- Use findByEmail here

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),   // <-- Change getUsername() to getEmail()
                user.getPassword(),
                Collections.singleton(() -> "ROLE_" + user.getRole())
        );
    }

}
