package com.jayant.AttendanceWebsite.controller;

import com.jayant.AttendanceWebsite.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String index() {
        return "login"; // Renders templates/login.html
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User()); // Binds empty user to the form
        return "signup"; // Renders templates/signup.html
    }

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) {
            return "admin/dashboard";
        } else if (role.equals("ROLE_TEACHER")) {
            return "teacher/dashboard";
        } else if (role.equals("ROLE_STUDENT")) {
            return "student/dashboard";
        } else {
            return "error"; // fallback
        }
    }
}
