package com.college.myproject.controller;

import com.college.myproject.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final JwtUtil jwtUtil;

    public ProfileController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public String profile(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);

        String email = jwtUtil.getEmailFromToken(token);

        return "Welcome " + email;
    }
}