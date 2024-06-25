package com.example.expensetracker.repository;

import com.example.expensetracker.model.entity.SharedAccount;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SharedAccountRepository extends JpaRepository<SharedAccount, UUID> {
    @EntityGraph("SharedAccount.accounts")
    Optional<SharedAccount> findById(UUID id);
}
