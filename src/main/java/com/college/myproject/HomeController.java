

package com.college.myproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to my first Spring Boot application!";
    }

    @GetMapping("/about")
    public String about() {
        return "This is my first Java web application.";
    }
}