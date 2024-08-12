package com.aurionpro.model;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Override
    public String validate(String email) {
        if (email == null || email.isEmpty()) {
            return "Email cannot be empty";
        }
        if (Pattern.matches(EMAIL_REGEX, email)) {
            return "true";
        } else {
            return "Invalid email format";
        }
    }
}
