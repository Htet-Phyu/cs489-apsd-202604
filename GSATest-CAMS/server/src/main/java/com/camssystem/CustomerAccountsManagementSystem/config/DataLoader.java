package com.camssystem.CustomerAccountsManagementSystem.config;

import com.camssystem.CustomerAccountsManagementSystem.entity.Account;
import com.camssystem.CustomerAccountsManagementSystem.entity.Customer;
import com.camssystem.CustomerAccountsManagementSystem.repository.AccountRepository;
import com.camssystem.CustomerAccountsManagementSystem.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public DataLoader(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (accountRepository.count() > 0) return;

        // Step 1: persist all accounts — they remain managed in this transaction
        Account ac1002 = accountRepository.save(new Account("AC1002", "Checking", LocalDate.of(2022, 7, 10), new BigDecimal("55900.50")));
        Account ac1001 = accountRepository.save(new Account("AC1001", "Savings",  LocalDate.of(2021, 11, 15), new BigDecimal("125.95")));
        Account ac1003 = accountRepository.save(new Account("AC1003", "Savings",  LocalDate.of(2022, 7, 11), new BigDecimal("75000.00")));

        // Step 2: persist all customers (no accounts yet)
        Customer bob    = customerRepository.save(new Customer("Bob",    "Jones"));
        Customer anna   = customerRepository.save(new Customer("Anna",   "Smith"));
        Customer carlos = customerRepository.save(new Customer("Carlos", "Jimenez"));

        // Step 3: link managed entities and save the owning side (Customer)
        bob.addAccount(ac1002);
        bob.addAccount(ac1001);
        anna.addAccount(ac1002);
        carlos.addAccount(ac1003);

        customerRepository.save(bob);
        customerRepository.save(anna);
        customerRepository.save(carlos);
    }
}
