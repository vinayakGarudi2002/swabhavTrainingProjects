package com.aurionpro.model;

public class Customer implements CustomerComponent {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDetails() {
        return "Name: " + firstName + " " + lastName + ", Email: " + email;
    }
}
