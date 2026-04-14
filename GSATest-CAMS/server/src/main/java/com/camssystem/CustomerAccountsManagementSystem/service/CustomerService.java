package com.camssystem.CustomerAccountsManagementSystem.service;

import com.camssystem.CustomerAccountsManagementSystem.dto.AccountDTO;
import com.camssystem.CustomerAccountsManagementSystem.dto.CreateCustomerAccountRequest;
import com.camssystem.CustomerAccountsManagementSystem.dto.CustomerDTO;
import com.camssystem.CustomerAccountsManagementSystem.entity.Account;
import com.camssystem.CustomerAccountsManagementSystem.entity.Customer;
import com.camssystem.CustomerAccountsManagementSystem.repository.AccountRepository;
import com.camssystem.CustomerAccountsManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public CustomerService(CustomerRepository customerRepository,
                           AccountRepository accountRepository,
                           AccountService accountService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    public List<CustomerDTO> getAllCustomersWithAccounts() {
        return customerRepository.findAllWithAccounts()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public CustomerDTO createCustomerWithAccount(CreateCustomerAccountRequest request) {
        Account account = new Account(
                request.getAccountNumber(),
                request.getAccountType(),
                request.getDateOpened(),
                request.getBalance()
        );
        account = accountRepository.save(account);

        Customer customer = new Customer(request.getFirstName(), request.getLastName());
        customer.addAccount(account);
        customer = customerRepository.save(customer);

        return toDTO(customer);
    }

    private CustomerDTO toDTO(Customer customer) {
        List<AccountDTO> accountDTOs = customer.getAccounts()
                .stream()
                .map(accountService::toDTO)
                .toList();

        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                accountDTOs
        );
    }
}
