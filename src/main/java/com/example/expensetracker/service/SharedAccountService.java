package com.example.expensetracker.service;

import com.example.expensetracker.mapper.SharedAccountMapper;
import com.example.expensetracker.model.dto.SharedAccountDto;
import com.example.expensetracker.model.entity.Account;
import com.example.expensetracker.model.entity.SharedAccount;
import com.example.expensetracker.repository.AccountRepository;
import com.example.expensetracker.repository.SharedAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SharedAccountService {
    final SharedAccountRepository repository;
    final SharedAccountMapper mapper;
    final AccountService service;
    private final AccountRepository accountRepository;

    public SharedAccountService(SharedAccountRepository repository, SharedAccountMapper mapper, AccountService service, AccountRepository accountRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
        this.accountRepository = accountRepository;
    }

    public SharedAccount getById(UUID id) {
        SharedAccount sharedAccount = repository.findById(id).orElseThrow(RuntimeException::new);
        double totalBalance = sharedAccount.getAccounts().stream()
                .mapToDouble(Account::getBalance)
                .sum();
        sharedAccount.setBalance(totalBalance);
        return sharedAccount;
    }

    @Transactional
    public void addAccount(UUID accountId, UUID sharedAccountId) {
        Account account = service.getById(accountId);
        SharedAccount sharedAccount = repository.findById(sharedAccountId).orElseThrow(RuntimeException::new);
        account.setSharedAccount(sharedAccount);
        accountRepository.save(account);
    }

    public void removeAccount(UUID accountId, UUID sharedAccountId) {
        repository.findById(sharedAccountId).ifPresent(sharedAccount -> {
            List<Account> accounts = sharedAccount.getAccounts();
            accounts.removeIf(account -> {
                boolean shouldRemove = account.getId().equals(accountId);
                if (shouldRemove) {
                    account.setSharedAccount(null);
                }
                return shouldRemove;
            });
            sharedAccount.setAccounts(accounts);
            repository.save(sharedAccount);
        });
    }


    public void create(SharedAccountDto dto) {
        repository.save(mapper.toSharedAccount(dto));
    }
}
