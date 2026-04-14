package com.camssystem.CustomerAccountsManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String accountNumber;

    @NotBlank
    @Column(nullable = false)
    private String accountType;

    private LocalDate dateOpened;

    @NotNull
    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();

    public Account() {}

    public Account(String accountNumber, String accountType, LocalDate dateOpened, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.dateOpened = dateOpened;
        this.balance = balance;
    }

    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public LocalDate getDateOpened() { return dateOpened; }
    public void setDateOpened(LocalDate dateOpened) { this.dateOpened = dateOpened; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Set<Customer> getCustomers() { return customers; }
    public void setCustomers(Set<Customer> customers) { this.customers = customers; }
}
