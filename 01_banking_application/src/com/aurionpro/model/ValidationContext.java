package com.aurionpro.model;

public class ValidationContext {
    private Validator validator;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public String validate(String input) {
        return validator.validate(input);
    }
}
