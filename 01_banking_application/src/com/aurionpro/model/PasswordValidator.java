package com.aurionpro.model;

public class PasswordValidator implements Validator {
    @Override
    public String validate(String password) {
        if (password == null || password.isEmpty()) {
            return "Password cannot be empty";
        }
        if (password.length() < 8) {
            return "Password must be at least 8 characters long";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter";
        }
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit";
        }
        if (!password.matches(".*[@#$%^&+=].*")) {
            return "Password must contain at least one special character (@#$%^&+=)";
        }
        return "true";
    }
}

