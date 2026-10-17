package com.college.myproject.controller;

import com.college.myproject.dto.LoginRequest;
import com.college.myproject.dto.RegisterRequest;
import com.college.myproject.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        return authService.login(
                request.getEmail(),
                request.getPassword()
        );
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        return authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
    }
}