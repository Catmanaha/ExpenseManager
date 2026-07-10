package com.example.expensetracker.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.expensetracker.model.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenProvider {
    private final String jwtSecret;
    private final Duration accessTokenLifetime;

    public TokenProvider(
            @Value("${security.jwt.secret}") String jwtSecret,
            @Value("${security.jwt.access-token-lifetime:PT2H}") Duration accessTokenLifetime
    ) {
        if (jwtSecret == null || jwtSecret.length() < 32) {
            throw new IllegalArgumentException(
                    "security.jwt.secret must contain at least 32 characters"
            );
        }

        this.jwtSecret = jwtSecret;
        this.accessTokenLifetime = accessTokenLifetime;
    }

    public String generateAccessToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withSubject(user.getUsername())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(genAccessExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (TokenExpiredException exception) {
            throw new TokenExpiredException("Token is expired", exception.getExpiredOn());
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    private Instant genAccessExpirationDate() {
        return Instant.now().plus(accessTokenLifetime);
    }
}
