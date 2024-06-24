package com.example.expensetracker.service;

import com.example.expensetracker.dto.AccountDto;
import com.example.expensetracker.mapper.AccountMapper;
import com.example.expensetracker.mapper.models.AccountMapperModel;
import com.example.expensetracker.model.Account;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    final AccountRepository repository;
    final AccountMapper mapper;
    final AuthService service;

    public AccountService(AccountRepository repository, AccountMapper mapper, AuthService service) {
        this.repository = repository;
        this.mapper = mapper;
        this.service = service;
    }

    public void create(AccountDto dto) {
        User user = service.getUserById(dto.userId());
        AccountMapperModel mm = new AccountMapperModel(dto.name(), user);
        repository.save(mapper.toAccount(mm));
    }

    public void update(UUID id, AccountDto dto) {
        Account account = repository.findById(id).orElseThrow(RuntimeException::new);
        User user = service.getUserById(dto.userId());
        AccountMapperModel mm = new AccountMapperModel(dto.name(), user);
        mapper.updateAccountFromMm(mm, account);
        repository.save(account);
    }

    public void update(Account account) {
        repository.save(account);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Account getById(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}
