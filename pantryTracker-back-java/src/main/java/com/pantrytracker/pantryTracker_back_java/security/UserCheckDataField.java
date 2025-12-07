package com.pantrytracker.pantryTracker_back_java.security;

import com.pantrytracker.pantryTracker_back_java.models.RegisterRequest;

public class UserCheckDataField {

    public static boolean isCorrectData(RegisterRequest request) {
        String userName = request.getUserName();
        String email = request.getEmail();
        String password = request.getPassword();

        if (userName == null || userName.length() < 3 || userName.length() > 20) {
            return false;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }

        if (password == null || password.length() < 6 || password.length() > 40) {
            return false;
        }

        return true;
    }

}
