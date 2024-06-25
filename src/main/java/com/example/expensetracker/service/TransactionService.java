package com.example.expensetracker.service;

import com.example.expensetracker.model.dto.TransactionDto;
import com.example.expensetracker.mapper.TransactionMapper;
import com.example.expensetracker.mapper.models.TransactionMapperModel;
import com.example.expensetracker.model.entity.Account;
import com.example.expensetracker.model.entity.Category;
import com.example.expensetracker.model.entity.Transaction;
import com.example.expensetracker.model.enums.TransactionType;
import com.example.expensetracker.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    final TransactionRepository repository;
    final TransactionMapper mapper;
    final CategoryService categoryService;
    final AccountService accountService;

    public TransactionService(TransactionRepository repository,
                              TransactionMapper mapper,
                              AccountService accountService,
                              CategoryService categoryService) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    public void create(TransactionDto dto) {
        Account account = accountService.getById(dto.accountId());
        if (dto.type() == TransactionType.INCOME) {
            account.setBalance(account.getBalance() + dto.amount());
        } else if (dto.type() == TransactionType.EXPENSE) {
            account.setBalance(account.getBalance() - dto.amount());
        }
        accountService.update(account);
        Category category = categoryService.getById(dto.categoryId());
        TransactionMapperModel mm = new TransactionMapperModel(dto.amount(), dto.type(), dto.description(), dto.dateOfTransaction(), category, account);
        repository.save(mapper.toTransaction(mm));
    }

    public void update(UUID id, TransactionDto dto) {
        Transaction transaction = repository.findById(id).orElseThrow(RuntimeException::new);
        Account account = accountService.getById(dto.accountId());
        Category category = categoryService.getById(dto.categoryId());
        TransactionMapperModel mm = new TransactionMapperModel(dto.amount(), dto.type(), dto.description(), dto.dateOfTransaction(), category, account);

        mapper.updateTransactionFromMm(mm, transaction);
        repository.save(transaction);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    public Transaction getById(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}
