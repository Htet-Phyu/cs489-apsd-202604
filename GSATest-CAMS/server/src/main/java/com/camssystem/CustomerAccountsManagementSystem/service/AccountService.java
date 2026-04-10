package com.camssystem.CustomerAccountsManagementSystem.service;

import com.camssystem.CustomerAccountsManagementSystem.dto.AccountDTO;
import com.camssystem.CustomerAccountsManagementSystem.entity.Account;
import com.camssystem.CustomerAccountsManagementSystem.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> getAllAccountsSortedByBalanceDesc() {
        return accountRepository.findAllByOrderByBalanceDesc()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public BigDecimal getTotalLiquidity() {
        return accountRepository.findAll()
                .stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<AccountDTO> getPrimeAccounts() {
        return accountRepository.findByBalanceGreaterThan(new BigDecimal("50000"))
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public AccountDTO toDTO(Account account) {
        return new AccountDTO(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getDateOpened(),
                account.getBalance()
        );
    }
}
