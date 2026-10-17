package com.ott.platform;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // This tells Spring Boot: when someone visits the home page website, show them "index"
    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}