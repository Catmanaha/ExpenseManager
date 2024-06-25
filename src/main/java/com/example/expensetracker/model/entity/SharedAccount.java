package com.example.expensetracker.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class SharedAccount {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double balance;
    @OneToMany(mappedBy = "sharedAccount", fetch = FetchType.EAGER)
    private List<Account> accounts;
}
