package com.college.myproject.service;

import com.college.myproject.dto.UpdateProfileRequest;
import com.college.myproject.exception.ResourceNotFoundException;
import com.college.myproject.model.User;
import com.college.myproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Retrieve user details by email.

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    //Update user profile details.

    @Transactional
    public User updateUser(String currentEmail, UpdateProfileRequest request) {
        User user = getUserByEmail(currentEmail);

        user.setName(request.getName());

        if (!request.getEmail().equals(currentEmail)) {
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email is already registered");
            }
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    //Delete user account by email.

    @Transactional
    public void deleteUserByEmail(String email) {
        User user = getUserByEmail(email);
        userRepository.delete(user);
    }
}