# ExpenseManager

ExpenseManager is a Spring Boot REST API I built to practise the backend of an expense tracking application. It handles users, accounts, shared accounts, categories, and transactions. The project does not connect to banks or process real payments.

## Main functionality

- JWT authentication with signed access tokens.
- BCrypt password hashing through Spring Security.
- Role checks on account, transaction, category, and shared-account routes.
- SQL Server persistence with Spring Data JPA.
- Optimistic locking on account updates to catch conflicting writes.

```text
REST controllers
      |
      v
Spring services ----> JPA repositories ----> SQL Server
      ^
      |
JWT security filter and Spring Security
```

## Stack

- Java 17
- Spring Boot 3.3
- Spring Security
- Spring Data JPA
- SQL Server
- Gradle

## Run locally

Create a SQL Server database, then copy the values from `.env.example` into your environment or IDE run configuration. The main settings are:

- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `JWT_SECRET`, with at least 32 random characters
- `JWT_ACCESS_TOKEN_LIFETIME`

The application stops during startup if the JWT secret is missing or too short.

Run it with:

```powershell
.\gradlew.bat bootRun --no-daemon
```

Do not commit a populated `.env` file.

## Tests and build

```powershell
.\gradlew.bat test --no-daemon
.\gradlew.bat bootJar --no-daemon
```

The current tests cover JWT generation, validation, tamper detection, and signing-key validation without needing a database. GitHub Actions runs the Gradle test task with Java 17.

## Still to do

- Add database integration and controller authorization tests.
- Add a migration tool instead of relying on Hibernate for schema setup.
- Replace generic runtime exceptions with a consistent API error model.
- Add refresh tokens and token revocation.
