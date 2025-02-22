package com.bankmgmt.app.service;

import com.bankmgmt.app.entity.Account;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private List<Account> accounts = new ArrayList<>();

    //  Create an account
    public Account createAccount(String name, String accountType, String email) {
        // Check for unique email
        for (Account acc : accounts) {
            if (acc.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("Email already exists!");
            }
        }
        // Validate account type
        if (!accountType.equalsIgnoreCase("SAVINGS") && !accountType.equalsIgnoreCase("CURRENT")) {
            throw new RuntimeException("Invalid account type. Must be SAVINGS or CURRENT.");
        }

        Account newAccount = new Account(name, accountType, email);
        accounts.add(newAccount);
        return newAccount;
    }

    //  Get all accounts
    public List<Account> getAllAccounts() {
        return accounts;
    }

    //  Get account by ID
    public Account getAccountById(Integer id) {
        return accounts.stream().filter(a -> a.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    //  Deposit money
    public Account depositMoney(Integer id, Double amount) {
        if (amount <= 0) throw new RuntimeException("Deposit amount must be positive");

        Account acc = getAccountById(id);
        acc.setBalance(acc.getBalance() + amount);
        return acc;
    }

    //  Withdraw money
    public Account withdrawMoney(Integer id, Double amount) {
        if (amount <= 0) throw new RuntimeException("Withdrawal amount must be positive");

        Account acc = getAccountById(id);
        if (acc.getBalance() < amount) throw new RuntimeException("Insufficient balance!");

        acc.setBalance(acc.getBalance() - amount);
        return acc;
    }

    //  Transfer money
    public void transferMoney(Integer fromId, Integer toId, Double amount) {
        if (amount <= 0) throw new RuntimeException("Transfer amount must be positive");

        Account sender = getAccountById(fromId);
        Account receiver = getAccountById(toId);

        if (sender.getBalance() < amount) throw new RuntimeException("Insufficient balance!");

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
    }

    //  Delete account
    public void deleteAccount(Integer id) {
        accounts.removeIf(a -> a.getId().equals(id));
    }
}
