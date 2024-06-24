package com.example.expensetracker.model;

import com.example.expensetracker.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "AccountTransaction")
public class Transaction {
    @Id
    @GeneratedValue
    private UUID id;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String description;
    private LocalDate dateOfTransaction;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Account account;
}
