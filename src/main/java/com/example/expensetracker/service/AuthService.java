package com.example.expensetracker.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.expensetracker.model.dto.SignUpDto;
import com.example.expensetracker.model.entity.User;
import com.example.expensetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    final
    UserRepository repository;

    public AuthService(UserRepository repository) {
        this.repository = repository;
    }

    public User getByUserId(UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByEmail(username).orElseThrow(RuntimeException::new);
    }

    public UserDetails signUp(SignUpDto data) throws JWTVerificationException {
        repository.findByEmail(data.email()).ifPresent(user -> {
            throw new JWTVerificationException("Username already exists");
        });
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(), data.email(), encryptedPassword, data.role());
        return repository.save(newUser);
    }
}
