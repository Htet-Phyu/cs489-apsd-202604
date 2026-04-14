package com.camssystem.CustomerAccountsManagementSystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDTO {

    private Long accountId;
    private String accountNumber;
    private String accountType;
    private LocalDate dateOpened;
    private BigDecimal balance;

    public AccountDTO() {}

    public AccountDTO(Long accountId, String accountNumber, String accountType,
                      LocalDate dateOpened, BigDecimal balance) {
        this.accountId = accountId;
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
}
