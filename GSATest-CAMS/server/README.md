# CAMS - Customer Accounts Management System

A Spring Boot REST API for managing customers and their bank accounts.

## Tech Stack

- Java 21
- Spring Boot 3.4.4
- Spring Web, Spring Data JPA, Spring Validation
- PostgreSQL
- Maven

## Project Structure

```
src/main/java/com/camssystem/CustomerAccountsManagementSystem/
├── config/         # DataLoader (sample data on startup)
├── controller/     # REST controllers
├── dto/            # Data Transfer Objects
├── entity/         # JPA entities
├── repository/     # Spring Data JPA repositories
└── service/        # Business logic
```

## Setup & Running

### Prerequisites

- Java 21+
- Maven 3.8+ (or use the included `./mvnw` wrapper)
- PostgreSQL 14+ installed via Homebrew

---

### Step 1 — Start PostgreSQL

```bash
brew services restart postgresql@14
```

> Replace `postgresql@14` with your installed version (e.g. `postgresql@16`).

---

### Step 2 — Create the database

Connect to PostgreSQL as the `postgres` user:

```bash
psql -U postgres
```

Enter your password when prompted (e.g. `admin`).

Inside the `psql` shell, run:

```sql
CREATE DATABASE cams_db;
```

Confirm it was created:

```sql
\l
```

Exit:

```sql
\q
```

---

### Step 3 — Configure credentials

Edit `src/main/resources/application.properties` with the credentials you used above:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cams_db
spring.datasource.username=postgres
spring.datasource.password=admin
```

---

### Step 4 — Run the application

From the `server/` directory:

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.  
JPA auto-creates the tables on first run and seeds the sample data automatically.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/accounts` | All accounts sorted by balance DESC |
| GET | `/api/accounts/liquidity` | Total balance across all accounts |
| GET | `/api/accounts/prime` | Accounts with balance > 50,000 |
| GET | `/api/customers` | All customers with their accounts |
| POST | `/api/create` | Create a new customer + account |

### POST /api/create — Request Body

```json
{
  "firstName": "Jane",
  "lastName": "Doe",
  "accountNumber": "AC2001",
  "accountType": "Savings",
  "dateOpened": "2024-01-15",
  "balance": 10000.00
}
```

## Sample Data (loaded on startup)

| Customer | Account | Type | Balance |
|----------|---------|------|---------|
| Bob Jones | AC1002 | Checking | 55,900.50 |
| Anna Smith | AC1002 | Checking | 55,900.50 (shared) |
| Bob Jones | AC1001 | Savings | 125.95 |
| Carlos Jimenez | AC1003 | Savings | 75,000.00 |

## Data Model

- **Customer** ↔ **Account**: Many-to-Many (via `customer_account` join table)
- Circular references are avoided using DTOs
