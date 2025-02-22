package com.bankmgmt.app.entity;

public class Account {
    private static int counter = 1;
    private Integer id;
    private String accountHolderName;
    private String accountType;
    private String email;
    private Double balance;

    // Constructor
    public Account(String accountHolderName, String accountType, String email) {
        this.id = counter++; // Auto-increment ID
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.email = email;
        this.balance = 0.0; // Initial balance set to 0.0
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
