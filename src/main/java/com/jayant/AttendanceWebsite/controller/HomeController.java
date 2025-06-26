package com.jayant.AttendanceWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/") //Tells if we are on the entry point of the website it will open this
    public String index(){
        return "index"; // Will look for templates/index.html
    }
}
