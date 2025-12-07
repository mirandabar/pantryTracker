package com.pantrytracker.pantryTracker_back_java.services;

import com.pantrytracker.pantryTracker_back_java.security.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.pantrytracker.pantryTracker_back_java.models.RegisterRequest;
import com.pantrytracker.pantryTracker_back_java.repositories.UserRepository;
import com.pantrytracker.pantryTracker_back_java.security.UserCheckDataField;
import com.pantrytracker.pantryTracker_back_java.security.PasswordEncoderConfig;
import com.pantrytracker.pantryTracker_back_java.models.UserBuilder;
import com.pantrytracker.pantryTracker_back_java.models.User;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private User user;
    private final JwtUtil jwtUtil;

    public RegisterService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> register(RegisterRequest request) {

        if (!UserCheckDataField.isCorrectData(request)) {
            System.out.println("Datos de registro incorrectos: " + request);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Datos de registro incorrectos");
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        String userName = request.getUserName();
        String email = request.getEmail();
        String passwordName = request.getPassword();

        if (userRepository.existsByUserName(userName)) {
            System.out.println("Nombre de usuario ya en uso: " + userName);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Nombre de usuario ya en uso");
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        if (userRepository.existsByEmail(email)) {
            System.out.println("Email ya en uso: " + email);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Email ya en uso");
            return ResponseEntity
                    .badRequest()
                    .body(errorResponse);
        }

        User newUser = new UserBuilder()
                .userName(userName)
                .email(email)
                .password(new PasswordEncoderConfig().encode(passwordName))
                .build();

        userRepository.save(newUser);

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("message", "Inicio de sesión exitoso");

        Map<String, String> dataResponse = new HashMap<>();
        String token = jwtUtil.generateToken(email);
        dataResponse.put("token", token);
        dataResponse.put("userName", userName);

        successResponse.put("data", dataResponse);

        return ResponseEntity.ok(successResponse);
    }

}