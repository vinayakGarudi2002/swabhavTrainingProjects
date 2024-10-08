package com.aurionpro.model;

import java.util.Date;

public class TransactionView {
  

	private int transactionId;
    private long senderAccount;
    private long receiverAccount;
    private String typeOfTransaction;
    private double amount;
    private Date transactionDate;

    // Constructor
    public TransactionView(int transactionId, long senderAccount, long receiverAccount, String typeOfTransaction, double amount, Date transactionDate) {
        this.transactionId = transactionId;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.typeOfTransaction = typeOfTransaction;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(long senderAccount) {
        this.senderAccount = senderAccount;
    }

    public long getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(long receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getTypeOfTransaction() {
        return typeOfTransaction;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    @Override
  	public String toString() {
  		return "TransactionView [transactionId=" + transactionId + ", senderAccount=" + senderAccount
  				+ ", receiverAccount=" + receiverAccount + ", typeOfTransaction=" + typeOfTransaction + ", amount="
  				+ amount + ", transactionDate=" + transactionDate + "]";
  	}
}
