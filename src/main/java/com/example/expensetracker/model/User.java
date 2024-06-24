package com.example.expensetracker.model;

import com.example.expensetracker.model.enums.UserRole;
import com.example.expensetracker.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    public User(String name, String email, String encryptedPassword, UserRole role) {
        this.password = encryptedPassword;
        this.email = email;
        this.role = role;
        this.name = name;
    }

    public User() {

    }
}