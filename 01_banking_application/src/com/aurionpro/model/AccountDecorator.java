package com.aurionpro.model;

//Decorator base class for AccountComponent
public abstract class AccountDecorator implements AccountComponent {
 protected AccountComponent decoratedAccount;

 public AccountDecorator(AccountComponent decoratedAccount) {
     this.decoratedAccount = decoratedAccount;
 }

 @Override
 public long getAccountNo() {
     return decoratedAccount.getAccountNo();
 }

 @Override
 public double getBalance() {
     return decoratedAccount.getBalance();
 }

 @Override
 public void setBalance(double balance) {
     decoratedAccount.setBalance(balance);
 }

 @Override
 public int getUserId() {
     return decoratedAccount.getUserId();
 }
}

