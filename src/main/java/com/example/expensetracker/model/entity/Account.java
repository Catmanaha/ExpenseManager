package com.example.expensetracker.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
    @ManyToOne
    private User user;

}
