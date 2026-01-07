package com.bank.smartbank.controller;

import com.bank.smartbank.entity.Account;
import com.bank.smartbank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // CREATE ACCOUNT
    @PostMapping("/create/{userId}")
    public Account createAccount(@PathVariable Long userId) {
        return accountService.createAccount(userId);
    }

    // DEPOSIT
    @PostMapping("/deposit/{accountId}")
    public Account deposit(@PathVariable Long accountId,
                           @RequestParam BigDecimal amount) {
        return accountService.deposit(accountId, amount);
    }

    // WITHDRAW
    @PostMapping("/withdraw/{accountId}")
    public Account withdraw(@PathVariable Long accountId,
                            @RequestParam BigDecimal amount) {
        return accountService.withdraw(accountId, amount);
    }
}
