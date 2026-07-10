# ExpenseManager

ExpenseManager is a Spring Boot REST API for users, accounts, shared accounts,
categories, and transactions. It demonstrates JWT authentication, BCrypt
password hashing, JPA persistence, and optimistic locking on account updates.

This is a learning backend, not a deployed finance product. It does not process
payments, connect to banks, or contain real financial data.

## Architecture

```text
REST controllers
      |
      v
Spring services ----> JPA repositories ----> SQL Server
      ^
      |
JWT security filter and Spring Security
```

Authentication endpoints issue signed access tokens. Other routes pass through
the JWT filter and Spring Security role checks. Passwords are encoded with the
configured BCrypt `PasswordEncoder`. Account entities use JPA optimistic
locking to detect conflicting writes rather than silently overwriting them.

## Requirements

- Java 17
- SQL Server

## Configuration

Copy `.env.example` values into your local environment or IDE run
configuration. Do not commit a populated `.env` file.

Required secrets:

- `DATABASE_PASSWORD`
- `JWT_SECRET`, containing at least 32 randomly generated characters

The application deliberately fails during startup if the signing secret is
missing or too short.

## Build and test

```powershell
.\gradlew.bat test --no-daemon
.\gradlew.bat bootJar --no-daemon
```

Tests exercise JWT generation, validation, tamper detection, and signing-key
validation without requiring a database. GitHub Actions runs the same Gradle
test task with Java 17.

## Security notes

- Authentication routes are limited to `POST /auth/**`; account, transaction,
  category, and shared-account routes require the configured roles.
- JWT signing material and database credentials are read from runtime
  configuration rather than source code.
- A signing key and database password existed in earlier Git history. Rotate
  any reused values; removing them from the current tree does not purge history.

## Current limitations

- There are no database integration or controller authorization tests yet.
- Database schema lifecycle still relies on Hibernate configuration rather than
  a committed migration tool.
- Several service methods use generic runtime exceptions and need a consistent
  API error model.
- Token revocation and refresh tokens are not implemented.
