package com.example.expensetracker.model.dto;

import com.example.expensetracker.model.enums.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public record TransactionDto(double amount,
                             TransactionType type,
                             String description,
                             LocalDate dateOfTransaction,
                             UUID categoryId,
                             UUID accountId) {
}
