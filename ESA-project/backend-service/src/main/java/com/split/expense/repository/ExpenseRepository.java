package com.split.expense.repository;

import com.split.expense.entity.Expense;
import com.split.expense.entity.Group;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
    List<Expense> findByGroup(Group group);
    List<Expense> findByPaidBy(User paidBy);
}
