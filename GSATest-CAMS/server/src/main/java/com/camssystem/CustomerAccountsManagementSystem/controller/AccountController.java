package com.camssystem.CustomerAccountsManagementSystem.controller;

import com.camssystem.CustomerAccountsManagementSystem.dto.AccountDTO;
import com.camssystem.CustomerAccountsManagementSystem.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccountsSortedByBalanceDesc());
    }

    @GetMapping("/liquidity")
    public ResponseEntity<Map<String, BigDecimal>> getTotalLiquidity() {
        BigDecimal total = accountService.getTotalLiquidity();
        return ResponseEntity.ok(Map.of("totalLiquidity", total));
    }

    @GetMapping("/prime")
    public ResponseEntity<List<AccountDTO>> getPrimeAccounts() {
        return ResponseEntity.ok(accountService.getPrimeAccounts());
    }
}
