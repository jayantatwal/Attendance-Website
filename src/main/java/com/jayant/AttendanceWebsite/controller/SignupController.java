package com.jayant.AttendanceWebsite.controller;

import com.jayant.AttendanceWebsite.model.User;
import com.jayant.AttendanceWebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") User user, Model model) {
        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            if (existingUser.getPassword() != null && !existingUser.getPassword().isEmpty()) {
                model.addAttribute("error", "User already has an account.");
                return "signup";
            }

            // Encrypt and set password
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(existingUser);

            model.addAttribute("success", "Account created successfully. Please login.");
            return "signup";
        } else {
            model.addAttribute("error", "Email not pre-registered. Contact admin.");
            return "signup";
        }
    }



}
