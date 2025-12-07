package com.pantrytracker.pantryTracker_back_java.models;

public class UserBuilder {
    private String userName;
    private String email;
    private String password;

    public UserBuilder userName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public User build() {
        User u = new User();
        u.setUserName(userName);
        u.setEmail(email);
        u.setPassword(password);
        return u;
    }
}

