package com.college.myproject.controller;

import com.college.myproject.model.User;
import com.college.myproject.security.JwtUtil;
import com.college.myproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final JwtUtil jwtUtil;
    private final UserService userService; // Added to fetch user details

    // Updated constructor to inject both JwtUtil and UserService
    public ProfileController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> profile(@RequestHeader("Authorization") String authHeader) {
        // Your teammate's existing logic to extract email from the token
        String token = authHeader.substring(7);
        String email = jwtUtil.getEmailFromToken(token);

        // Fetch the full user details from the database using the email
        User user = userService.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Returns the user profile details (name, email, etc.) as JSON
        return ResponseEntity.ok(user);
    }
}