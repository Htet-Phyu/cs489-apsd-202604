package com.split.expense.service;

import com.split.expense.entity.*;
import com.split.expense.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseParticipantRepository participantRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final BalanceRepository balanceRepository;
    private final ActivityLogRepository activityLogRepository;

    /** Add an expense split equally among the given participant IDs. */
    @Transactional
    public Expense addEqualExpense(UUID groupId, UUID paidByUserId,
                                   String description, BigDecimal amount,
                                   LocalDate expenseDate, List<UUID> participantIds) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Expense amount must be positive.");
        }

        Group group = findGroup(groupId);
        User paidBy = findUser(paidByUserId);

        Expense expense = expenseRepository.save(Expense.builder()
                .group(group).paidBy(paidBy).description(description)
                .amount(amount).expenseDate(expenseDate).splitType("EQUAL").build());

        BigDecimal share = amount.divide(BigDecimal.valueOf(participantIds.size()), 2, RoundingMode.HALF_UP);

        for (UUID uid : participantIds) {
            User participant = findUser(uid);
            BigDecimal paid = uid.equals(paidByUserId) ? amount : BigDecimal.ZERO;
            participantRepository.save(ExpenseParticipant.builder()
                    .expense(expense).user(participant)
                    .shareAmount(share).paidAmount(paid).build());
            if (!uid.equals(paidByUserId)) {
                adjustBalance(group, participant, paidBy, share);
            }
        }

        log(paidBy, group, expense, "EXPENSE_ADDED",
                "Added '" + description + "' $" + amount + " paid by " + paidBy.getName());
        return expense;
    }

    /** Add an expense with a custom shares map: userId -> shareAmount. */
    @Transactional
    public Expense addUnequalExpense(UUID groupId, UUID paidByUserId,
                                     String description, BigDecimal amount,
                                     LocalDate expenseDate, Map<UUID, BigDecimal> shares) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Expense amount must be positive.");
        }

        Group group = findGroup(groupId);
        User paidBy = findUser(paidByUserId);

        Expense expense = expenseRepository.save(Expense.builder()
                .group(group).paidBy(paidBy).description(description)
                .amount(amount).expenseDate(expenseDate).splitType("UNEQUAL").build());

        for (Map.Entry<UUID, BigDecimal> entry : shares.entrySet()) {
            UUID uid = entry.getKey();
            User participant = findUser(uid);
            BigDecimal paid = uid.equals(paidByUserId) ? amount : BigDecimal.ZERO;
            participantRepository.save(ExpenseParticipant.builder()
                    .expense(expense).user(participant)
                    .shareAmount(entry.getValue()).paidAmount(paid).build());
            if (!uid.equals(paidByUserId)) {
                adjustBalance(group, participant, paidBy, entry.getValue());
            }
        }

        log(paidBy, group, expense, "EXPENSE_ADDED",
                "Added '" + description + "' $" + amount + " (unequal split)");
        return expense;
    }

    public List<Expense> getExpensesByGroup(UUID groupId) {
        return expenseRepository.findByGroup(findGroup(groupId));
    }

    // ── helpers ─────────────────────────────────────────────────────────────

    /**
     * Net-aware balance update: if a reverse balance already exists, reduce it first;
     * create/increment a forward balance otherwise.
     */
    private void adjustBalance(Group group, User owedBy, User owedTo, BigDecimal amount) {
        balanceRepository.findByGroupAndOwedByAndOwedTo(group, owedTo, owedBy).ifPresentOrElse(
                reverse -> {
                    BigDecimal remaining = reverse.getAmount().subtract(amount);
                    if (remaining.compareTo(BigDecimal.ZERO) > 0) {
                        reverse.setAmount(remaining);
                        balanceRepository.save(reverse);
                    } else if (remaining.compareTo(BigDecimal.ZERO) < 0) {
                        balanceRepository.delete(reverse);
                        balanceRepository.save(Balance.builder()
                                .group(group).owedBy(owedBy).owedTo(owedTo)
                                .amount(remaining.abs()).build());
                    } else {
                        balanceRepository.delete(reverse);
                    }
                },
                () -> balanceRepository.findByGroupAndOwedByAndOwedTo(group, owedBy, owedTo)
                        .ifPresentOrElse(
                                existing -> {
                                    existing.setAmount(existing.getAmount().add(amount));
                                    balanceRepository.save(existing);
                                },
                                () -> balanceRepository.save(Balance.builder()
                                        .group(group).owedBy(owedBy).owedTo(owedTo)
                                        .amount(amount).build())
                        )
        );
    }

    private void log(User actor, Group group, Expense expense, String action, String details) {
        activityLogRepository.save(ActivityLog.builder()
                .actorUser(actor).group(group).expense(expense)
                .action(action).details(details).build());
    }

    private Group findGroup(UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + id));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }
}
