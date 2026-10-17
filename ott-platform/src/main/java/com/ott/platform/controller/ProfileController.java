package com.ott.platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    // Show the Profile Page
    @GetMapping("/profile")
    public String showProfile(Model model) {
        // Passing some mock user details for now
        model.addAttribute("username", "Karthikeyan");
        model.addAttribute("email", "karthikeyan@ott.com");
        model.addAttribute("plan", "Premium HD");
        return "profile";
    }

    // Show the Subscription Plans Page (No payments, just plans!)
    @GetMapping("/subscription")
    public String showSubscriptionPlans() {
        return "subscription";
    }
}