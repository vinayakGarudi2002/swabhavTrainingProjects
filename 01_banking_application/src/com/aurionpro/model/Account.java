package com.aurionpro.model;

public class Account implements AccountComponent {
    private long accountNo;
    private double balance;
    private int userId; // user_id from the database

    public Account(long accountNo, double balance, int userId) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.userId = userId;
    }

    @Override
    public long getAccountNo() {
        return accountNo;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int getUserId() {
        return userId;
    }
}

