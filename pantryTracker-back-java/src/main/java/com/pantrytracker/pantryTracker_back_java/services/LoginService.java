package com.pantrytracker.pantryTracker_back_java.services;

import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.pantrytracker.pantryTracker_back_java.models.LoginRequest;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import com.pantrytracker.pantryTracker_back_java.security.PasswordEncoderConfig;
import com.pantrytracker.pantryTracker_back_java.security.JwtUtil;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig encoder = new PasswordEncoderConfig();
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    private boolean isPasswordCorrect(String email, String password) {
        String storedPassword = userRepository.getPasswordHashByEmail(email);
        return encoder.matches(password, storedPassword);
    }

    public ResponseEntity<?> login(LoginRequest request) {

        String email = request.getEmail();
        String password = request.getPassword();

        if (!userRepository.existsByEmail(email)) {
            System.out.println("Usuario no encontrado con email: " + email);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Usuario no encontrado");
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        if (!this.isPasswordCorrect(email, password)) {
            System.out.println("Contraseña incorrecta para el usuario con email: " + email);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Contraseña incorrecta");
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        String userName = userRepository.getUserNameByEmail(email);

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Inicio de sesión exitoso");

        Map<String, String> dataResponse = new HashMap<>();
        String token = jwtUtil.generateToken(userName);
        dataResponse.put("token", token);
        dataResponse.put("userName", userName);

        successResponse.put("data", dataResponse);

        return ResponseEntity.ok(successResponse);
    }
}
