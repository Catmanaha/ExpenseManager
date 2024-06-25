package com.example.expensetracker.model.dto;

import com.example.expensetracker.model.enums.UserRole;

public record SignUpDto(String name,
                        String email,
                        String password,
                        UserRole role) {
}
