package com.example.expensetracker.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NamedEntityGraph(name = "SharedAccount.accounts",
        attributeNodes = @NamedAttributeNode("accounts"))
public class SharedAccount {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private double balance;
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "sharedAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Account> accounts;
}
