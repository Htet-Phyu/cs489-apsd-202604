package com.camssystem.CustomerAccountsManagementSystem.dto;

import java.util.List;

public class CustomerDTO {

    private Long customerId;
    private String firstName;
    private String lastName;
    private List<AccountDTO> accounts;

    public CustomerDTO() {}

    public CustomerDTO(Long customerId, String firstName, String lastName, List<AccountDTO> accounts) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accounts = accounts;
    }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public List<AccountDTO> getAccounts() { return accounts; }
    public void setAccounts(List<AccountDTO> accounts) { this.accounts = accounts; }
}
