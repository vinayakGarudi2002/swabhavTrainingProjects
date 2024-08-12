package com.aurionpro.model;

public class CustomerView {
    private String firstName;
    private String lastName;
    private long accountNo;
    private double balance;

    // Constructor
    public CustomerView(String firstName, String lastName, long accountNo, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNo = accountNo;
        this.balance = balance;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
