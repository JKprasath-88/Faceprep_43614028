package com.healthcare.system.controller;

import com.healthcare.system.entity.User;
import com.healthcare.system.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    // UPDATED login method
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User input) {
        Optional<User> userOpt = userRepository.findByEmail(input.getEmail());
        Map<String, String> body = new HashMap<>();

        if (userOpt.isPresent() &&
                passwordEncoder.matches(input.getPassword(), userOpt.get().getPassword())) {

            body.put("message", "Login success");
            body.put("role", userOpt.get().getRole());   // "DOCTOR" or "PATIENT"
            return ResponseEntity.ok(body);
        }

        body.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(body);
    }
}
