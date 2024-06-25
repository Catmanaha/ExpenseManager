package com.example.expensetracker.mapper.models;

import com.example.expensetracker.model.entity.Account;
import com.example.expensetracker.model.entity.Category;
import com.example.expensetracker.model.enums.TransactionType;

import java.time.LocalDate;

public record TransactionMapperModel(double amount,
                                    TransactionType type,
                                    String description,
                                    LocalDate dateOfTransaction,
                                    Category category,
                                    Account account) {
}
