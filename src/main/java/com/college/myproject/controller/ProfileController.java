package com.college.myproject.controller;

import com.college.myproject.dto.UpdateProfileRequest;
import com.college.myproject.model.User;
import com.college.myproject.security.JwtUtil;
import com.college.myproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    public ProfileController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> profile(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestHeader(value = "Authorization", required = false) String authHeader,
                                           @Valid @RequestBody UpdateProfileRequest request) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);
        User updatedUser = userService.updateUser(email, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid authorization token");
        }
        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
        String email = jwtUtil.getEmailFromToken(token);
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("Account deleted successfully");
    }
}