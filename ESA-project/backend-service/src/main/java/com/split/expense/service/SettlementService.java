package com.split.expense.service;

import com.split.expense.entity.*;
import com.split.expense.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SettlementService {

    private final SettlementRepository settlementRepository;
    private final BalanceRepository balanceRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ActivityLogRepository activityLogRepository;

    @Transactional
    public Settlement settle(UUID groupId, UUID payerUserId, UUID payeeUserId, BigDecimal amount) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        User payer = userRepository.findById(payerUserId)
                .orElseThrow(() -> new IllegalArgumentException("Payer not found: " + payerUserId));
        User payee = userRepository.findById(payeeUserId)
                .orElseThrow(() -> new IllegalArgumentException("Payee not found: " + payeeUserId));

        Settlement settlement = settlementRepository.save(Settlement.builder()
                .group(group).payer(payer).payee(payee).amount(amount).build());

        // Reduce the existing balance
        balanceRepository.findByGroupAndOwedByAndOwedTo(group, payer, payee).ifPresent(balance -> {
            BigDecimal remaining = balance.getAmount().subtract(amount);
            if (remaining.compareTo(BigDecimal.ZERO) <= 0) {
                balanceRepository.delete(balance);
            } else {
                balance.setAmount(remaining);
                balanceRepository.save(balance);
            }
        });

        activityLogRepository.save(ActivityLog.builder()
                .actorUser(payer).group(group)
                .action("SETTLEMENT_MADE")
                .details(payer.getName() + " paid " + payee.getName() + " $" + amount)
                .build());

        return settlement;
    }

    public List<Balance> getBalancesForGroup(UUID groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        return balanceRepository.findByGroup(group);
    }

    public List<Settlement> getSettlementsForGroup(UUID groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        return settlementRepository.findByGroup(group);
    }
}
