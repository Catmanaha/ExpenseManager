package com.example.expensetracker.model.entity;

import com.example.expensetracker.model.enums.UserRole;
import com.example.expensetracker.model.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "`user`")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}