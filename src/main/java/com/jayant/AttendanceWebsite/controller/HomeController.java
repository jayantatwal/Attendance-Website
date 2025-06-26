package com.jayant.AttendanceWebsite.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/login") //Tells if we are on the entry point of the website it will open this
    public String index(){
        return "login"; // Will look for templates/index.html
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
            return "error"; // fallback if role is unknown
        }
    }
}
