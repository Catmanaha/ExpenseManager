package com.example.expensetracker.provider;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.expensetracker.model.entity.User;
import com.example.expensetracker.model.enums.UserRole;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenProviderTests {
    private static final String TEST_SECRET =
            "local-test-signing-key-with-at-least-32-characters";

    @Test
    void generatedTokenValidatesToTheUserEmail() {
        TokenProvider provider = new TokenProvider(TEST_SECRET, Duration.ofMinutes(5));
        User user = new User(
                "Test User",
                "test@example.com",
                "not-used-by-token-test",
                UserRole.USER
        );

        String token = provider.generateAccessToken(user);

        assertEquals("test@example.com", provider.validateToken(token));
    }

    @Test
    void alteredTokenIsRejected() {
        TokenProvider provider = new TokenProvider(TEST_SECRET, Duration.ofMinutes(5));
        User user = new User(
                "Test User",
                "test@example.com",
                "not-used-by-token-test",
                UserRole.USER
        );
        String token = provider.generateAccessToken(user);
        String alteredToken = token.substring(0, token.length() - 1) + "x";

        assertThrows(JWTVerificationException.class, () -> provider.validateToken(alteredToken));
    }

    @Test
    void shortSigningSecretIsRejected() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new TokenProvider("too-short", Duration.ofMinutes(5))
        );
    }
}
