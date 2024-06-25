package com.example.expensetracker.controller;

import com.example.expensetracker.model.dto.SharedAccountDto;
import com.example.expensetracker.service.SharedAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sharedAccounts")
public class SharedAccountController {
    final SharedAccountService service;

    public SharedAccountController(SharedAccountService service) {
        this.service = service;
    }

    @PostMapping
    public void create(@RequestBody SharedAccountDto dto) {
        service.create(dto);
    }

    @PostMapping("/{sharedAccountId}/{accountId}")
    public void addAccount(@PathVariable UUID sharedAccountId, @PathVariable UUID accountId) {
        service.addAccount(accountId, sharedAccountId);
    }

    @DeleteMapping("/{sharedAccountId}/{accountId}")
    public void removeAccount(@PathVariable UUID sharedAccountId, @PathVariable UUID accountId) {
        service.addAccount(accountId, sharedAccountId);
    }

    @GetMapping("/{id}")
    public void getById(@PathVariable UUID id) {
        service.getById(id);
    }
}
