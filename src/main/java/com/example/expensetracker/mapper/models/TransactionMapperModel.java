package com.example.expensetracker.mapper.models;

import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.Category;
import com.example.expensetracker.model.enums.TransactionType;

import java.time.LocalDate;
import java.util.UUID;

public record TransactionMapperModel(double amount,
                                    TransactionType type,
                                    String description,
                                    LocalDate dateOfTransaction,
                                    Category category,
                                    Account account) {
}
