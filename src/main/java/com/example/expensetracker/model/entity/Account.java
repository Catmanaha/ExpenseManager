package com.example.expensetracker.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double balance;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private SharedAccount sharedAccount;
}
