package com.bank.smartbank.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // DEPOSIT / WITHDRAW
    private BigDecimal amount;
    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
