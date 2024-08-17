package com.aurionpro.model;

public class AlphabeticValidator implements Validator {

    @Override
    public String validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Input cannot be null or empty.";
        }
        
        if (!input.matches("[a-zA-Z]+")) {
            return "Input must contain only alphabetic characters.";
        }

        return "true";  // Return null if the input is valid
    }
}
