package com.pantrytracker.pantryTracker_back_java.controllers;

import com.pantrytracker.pantryTracker_back_java.models.LoginRequest;
import com.pantrytracker.pantryTracker_back_java.models.RegisterRequest;
import com.pantrytracker.pantryTracker_back_java.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // necesario para React
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

}
