package com.example.expensetracker.service;

import com.example.expensetracker.dto.SignUpDto;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.security.KeyStore;
import java.util.UUID;

@Service
public class AuthService {
    final
    UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }


    public User getUserById(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    public User signUp(SignUpDto data) {
        if (repository.findByEmail(data.email()) != null) {
            throw new RuntimeException("Email already exists");
        }
        User newUser = new User(data.name(), data.email(), data.password(), data.role());
        return repository.save(newUser);
    }
}
