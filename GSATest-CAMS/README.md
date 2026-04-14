# CAMS — Customer-Accounts Management System

A full-stack web application for **Union Bank of Iowa** to manage customer accounts.  
Built as a GSA Selection Exercise for CS489 – Applied Software Development (April 2026).

---

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React 19, React Router, plain CSS |
| Backend | Java 21, Spring Boot 3.4.4, Spring Web, Spring Data JPA |
| Database | PostgreSQL |
| Build | Maven (backend), npm (frontend) |

---

## Project Structure

```
CAMS/
├── client/          # React frontend (port 3000)
└── server/          # Spring Boot backend (port 8081)
```

---

## Quick Start

### Step 1 — Start PostgreSQL and create the database

```bash
brew services restart postgresql@14
psql -U postgres        # enter password: admin
```

Inside psql:

```sql
CREATE DATABASE cams_db;
\l      -- confirm it appears in the list
\q      -- exit
```

### Step 2 — Start the backend

```bash
cd server
./mvnw spring-boot:run
```

Backend starts at `http://localhost:8081`.  
Database tables are auto-created and sample data is seeded on first run.

### Step 3 — Start the frontend

Open a **new terminal**:

```bash
cd client
npm install       # only needed once
npm start
```

Frontend opens at `http://localhost:3000`.

---

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `http://localhost:8081/api/accounts` | All accounts sorted by balance DESC |
| GET | `http://localhost:8081/api/accounts/liquidity` | Total liquidity (sum of all balances) |
| GET | `http://localhost:8081/api/accounts/prime` | Prime accounts (balance > $50,000) |
| GET | `http://localhost:8081/api/customers` | All customers with their accounts |
| POST | `http://localhost:8081/api/create` | Create new customer + account |

---

## Sample Data (seeded on startup)

**Customers**

| ID | First Name | Last Name |
|----|-----------|----------|
| 1 | Bob | Jones |
| 2 | Anna | Smith |
| 3 | Carlos | Jimenez |

**Accounts**

| Account # | Type | Date Opened | Balance | Customer(s) |
|-----------|------|-------------|---------|-------------|
| AC1002 | Checking | 2022-07-10 | $55,900.50 | Bob Jones, Anna Smith |
| AC1001 | Savings | 2021-11-15 | $125.95 | Bob Jones |
| AC1003 | Savings | 2022-07-11 | $75,000.00 | Carlos Jimenez |

**Total Liquidity: $131,026.45**

---

## Application Pages

| Route | Description |
|-------|-------------|
| `/` | Home page — system overview and navigation |
| `/accounts` | All accounts table + liquidity position + prime accounts |
| `/create` | Form to register a new customer and account |

---

## Data Model

- **Customer** ↔ **Account**: Many-to-Many relationship
- Join table: `customer_account`
- Circular JSON references avoided via DTOs
