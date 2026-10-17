
package com.college.myproject.service;

import com.college.myproject.model.User;
import com.college.myproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.college.myproject.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            return "Invalid email or password";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid email or password";
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    public String register(String name, String email, String password) {

        User existingUser = userRepository.findByEmail(email).orElse(null);

        if (existingUser != null) {
            return "Email already registered";
        }

        String encodedPassword= passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);

        userRepository.save(user);

        return "Registration successful";
    }
}