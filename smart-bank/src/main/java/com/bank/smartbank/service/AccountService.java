package com.bank.smartbank.service;

import com.bank.smartbank.entity.*;
import com.bank.smartbank.repository.AccountRepository;
import com.bank.smartbank.repository.TransactionRepository;
import com.bank.smartbank.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository,
                          UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    // 1️⃣ CREATE BANK ACCOUNT
    public Account createAccount(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setAccountNumber(UUID.randomUUID().toString().substring(0, 12));
        account.setBalance(BigDecimal.ZERO);
        account.setStatus(AccountStatus.ACTIVE);
        account.setUser(user);

        return accountRepository.save(account);
    }

    // 2️⃣ DEPOSIT MONEY
    @Transactional
    public Account deposit(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance().add(amount));

        Transaction tx = new Transaction();
        tx.setType("DEPOSIT");
        tx.setAmount(amount);
        tx.setTransactionDate(LocalDateTime.now());
        tx.setAccount(account);

        transactionRepository.save(tx);

        return account;
    }

    // 3️⃣ WITHDRAW MONEY
    @Transactional
    public Account withdraw(Long accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance().subtract(amount));

        Transaction tx = new Transaction();
        tx.setType("WITHDRAW");
        tx.setAmount(amount);
        tx.setTransactionDate(LocalDateTime.now());
        tx.setAccount(account);

        transactionRepository.save(tx);

        return account;
    }
}
