package com.split.expense.repository;

import com.split.expense.entity.Expense;
import com.split.expense.entity.ExpenseParticipant;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseParticipantRepository extends JpaRepository<ExpenseParticipant, UUID> {
    List<ExpenseParticipant> findByExpense(Expense expense);
    List<ExpenseParticipant> findByUser(User user);
}
