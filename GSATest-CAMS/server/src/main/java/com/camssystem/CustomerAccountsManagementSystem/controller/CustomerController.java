package com.camssystem.CustomerAccountsManagementSystem.controller;

import com.camssystem.CustomerAccountsManagementSystem.dto.CreateCustomerAccountRequest;
import com.camssystem.CustomerAccountsManagementSystem.dto.CustomerDTO;
import com.camssystem.CustomerAccountsManagementSystem.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomersWithAccounts());
    }

    @PostMapping("/api/create")
    public ResponseEntity<CustomerDTO> createCustomerWithAccount(
            @Valid @RequestBody CreateCustomerAccountRequest request) {
        CustomerDTO created = customerService.createCustomerWithAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
