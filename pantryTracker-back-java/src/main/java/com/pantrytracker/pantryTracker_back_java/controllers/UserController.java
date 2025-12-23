package com.pantrytracker.pantryTracker_back_java.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import com.pantrytracker.pantryTracker_back_java.models.User;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        String username = authentication.getName(); // lo extrae del JWT
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }
        return ResponseEntity.ok(new UserResponse(user.get().getUserName()));
    }

    public record UserResponse(String userName) {}
}

