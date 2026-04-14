package com.camssystem.CustomerAccountsManagementSystem.repository;

import com.camssystem.CustomerAccountsManagementSystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByOrderByBalanceDesc();

    List<Account> findByBalanceGreaterThan(BigDecimal threshold);
}
