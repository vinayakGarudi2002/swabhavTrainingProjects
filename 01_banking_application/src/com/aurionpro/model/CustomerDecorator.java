package com.aurionpro.model;

public abstract class CustomerDecorator implements CustomerComponent {
    protected CustomerComponent decoratedCustomer;

    public CustomerDecorator(CustomerComponent decoratedCustomer) {
        this.decoratedCustomer = decoratedCustomer;
    }

    @Override
    public String getFirstName() {
        return decoratedCustomer.getFirstName();
    }

    @Override
    public String getLastName() {
        return decoratedCustomer.getLastName();
    }

    @Override
    public String getEmail() {
        return decoratedCustomer.getEmail();
    }

    @Override
    public String getPassword() {
        return decoratedCustomer.getPassword();
    }

    @Override
    public String getDetails() {
        return decoratedCustomer.getDetails();
    }
}

