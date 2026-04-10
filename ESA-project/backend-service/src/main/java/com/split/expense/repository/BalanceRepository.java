package com.split.expense.repository;

import com.split.expense.entity.Balance;
import com.split.expense.entity.Group;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, UUID> {
    List<Balance> findByGroup(Group group);
    List<Balance> findByOwedBy(User owedBy);
    List<Balance> findByOwedTo(User owedTo);
    Optional<Balance> findByGroupAndOwedByAndOwedTo(Group group, User owedBy, User owedTo);
}
