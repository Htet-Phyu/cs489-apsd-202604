package com.camssystem.CustomerAccountsManagementSystem.repository;

import com.camssystem.CustomerAccountsManagementSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.accounts")
    List<Customer> findAllWithAccounts();
}
