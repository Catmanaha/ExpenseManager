package com.example.expensetracker.service;

import com.example.expensetracker.mapper.SharedAccountMapper;
import com.example.expensetracker.model.dto.SharedAccountDto;
import com.example.expensetracker.model.entity.Account;
import com.example.expensetracker.model.entity.SharedAccount;
import com.example.expensetracker.repository.SharedAccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SharedAccountService {
    final SharedAccountRepository repository;
    final SharedAccountMapper mapper;

    public SharedAccountService(SharedAccountRepository repository, SharedAccountMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SharedAccount getById(UUID id) {
        SharedAccount sharedAccount = repository.findById(id).orElseThrow(RuntimeException::new);
        double totalBalance = sharedAccount.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
        sharedAccount.setBalance(totalBalance);
        return sharedAccount;
    }

    public void addAccount(UUID accountId, UUID sharedAccountId) {
        repository.findById(sharedAccountId).ifPresent(sharedAccount -> {
            Account account = new Account();
            account.setId(accountId);
            sharedAccount.getAccounts().add(account);
            repository.save(sharedAccount);
        });
    }

    public void removeAccount(UUID accountId, UUID sharedAccountId) {
        repository.findById(sharedAccountId).ifPresent(sharedAccount -> {
            sharedAccount.getAccounts().removeIf(account -> account.getId().equals(accountId));
            repository.save(sharedAccount);
        });
    }

    public void create(SharedAccountDto dto) {
        repository.save(mapper.toSharedAccount(dto));
    }
}
