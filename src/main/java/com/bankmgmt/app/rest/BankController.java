package com.bankmgmt.app.rest;

import com.bankmgmt.app.entity.Account;
import com.bankmgmt.app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestParam String name, 
                                                 @RequestParam String accountType, 
                                                 @RequestParam String email) {
        Account account = bankService.createAccount(name, accountType, email);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(bankService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id) {
        return new ResponseEntity<>(bankService.getAccountById(id), HttpStatus.OK);
    }

    @PostMapping("/accounts/{id}/deposit")
    public ResponseEntity<Account> depositMoney(@PathVariable Integer id, @RequestParam Double amount) {
        return new ResponseEntity<>(bankService.depositMoney(id, amount), HttpStatus.OK);
    }

    @PostMapping("/accounts/{id}/withdraw")
    public ResponseEntity<Account> withdrawMoney(@PathVariable Integer id, @RequestParam Double amount) {
        return new ResponseEntity<>(bankService.withdrawMoney(id, amount), HttpStatus.OK);
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<String> transferMoney(@RequestParam Integer fromId, 
                                                @RequestParam Integer toId, 
                                                @RequestParam Double amount) {
        bankService.transferMoney(fromId, toId, amount);
        return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        bankService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
