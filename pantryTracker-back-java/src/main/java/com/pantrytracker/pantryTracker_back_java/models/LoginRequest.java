package com.pantrytracker.pantryTracker_back_java.models;

/**
 * Clase que recoge la información que como viene el dato de la solicitud de Login.
 */

public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
