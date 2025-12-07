package com.pantrytracker.pantryTracker_back_java.services;

import com.pantrytracker.pantryTracker_back_java.models.LoginRequest;
import com.pantrytracker.pantryTracker_back_java.models.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final LoginService loginService;
    private final RegisterService registerService;

    public AuthService(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    public ResponseEntity<?> login(LoginRequest request) {
        return loginService.login(request);
    }

    public ResponseEntity<?> register(RegisterRequest request) {
        return registerService.register(request);
    }
}

