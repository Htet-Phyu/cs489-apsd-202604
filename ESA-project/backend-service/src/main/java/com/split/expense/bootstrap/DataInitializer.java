package com.split.expense.bootstrap;

import com.split.expense.entity.*;
import com.split.expense.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Inserts the exact sample data shown in the screenshots.
 * Runs first (Order 1) so that AppDemoRunner can reference the seeded rows.
 */
@Slf4j
@Component
@Order(1)
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final FriendRepository friendRepo;
    private final GroupRepository groupRepo;
    private final GroupMemberRepository groupMemberRepo;
    private final ExpenseRepository expenseRepo;
    private final ExpenseParticipantRepository participantRepo;
    private final BalanceRepository balanceRepo;
    private final SettlementRepository settlementRepo;
    private final ActivityLogRepository activityLogRepo;

    @Override
    @Transactional
    public void run(String... args) {
        log.info("=== [DataInitializer] Inserting sample data ===");

        // ── Users (U1, U2, U3) ───────────────────────────────────────────────
        User u1 = userRepo.save(User.builder()
                .name("John Doe").email("john@email.com").phone("12345")
                .createdAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        User u2 = userRepo.save(User.builder()
                .name("Alice Kim").email("alice@email.com").phone("98765")
                .createdAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        User u3 = userRepo.save(User.builder()
                .name("Bob Lee").email("bob@email.com").phone("55544")
                .createdAt(LocalDateTime.of(2026, 4, 2, 0, 0)).build());

        log.info("[SEED] Users: {}, {}, {}", u1, u2, u3);

        // ── Friends (F1, F2, F3) ─────────────────────────────────────────────
        // F1 – U1 added U2 (has account)
        friendRepo.save(Friend.builder()
                .ownerUser(u1).friendUser(u2)
                .name("Alice Kim").email("alice@email.com").phone("98765")
                .createdAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        // F2 – U1 added Mike Chen (no account → friendUser is null)
        friendRepo.save(Friend.builder()
                .ownerUser(u1).friendUser(null)
                .name("Mike Chen").email("mike@gmail.com").phone("77777")
                .createdAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        // F3 – U2 added U3 (has account)
        friendRepo.save(Friend.builder()
                .ownerUser(u2).friendUser(u3)
                .name("Bob Lee").email("bob@email.com").phone("55544")
                .createdAt(LocalDateTime.of(2026, 4, 2, 0, 0)).build());

        log.info("[SEED] Friends inserted.");

        // ── Groups (G1 = Trip 2026, G2 = Roommates) ──────────────────────────
        Group g1 = groupRepo.save(Group.builder()
                .name("Trip 2026").createdBy(u1)
                .createdAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());

        Group g2 = groupRepo.save(Group.builder()
                .name("Roommates").createdBy(u1)
                .createdAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());

        log.info("[SEED] Groups: {}, {}", g1, g2);

        // ── Group Members ────────────────────────────────────────────────────
        // G1: U1=OWNER, U2=MEMBER
        groupMemberRepo.save(GroupMember.builder().group(g1).user(u1).role("OWNER")
                .joinedAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());
        groupMemberRepo.save(GroupMember.builder().group(g1).user(u2).role("MEMBER")
                .joinedAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());

        // G2: U1=OWNER, U3=MEMBER
        groupMemberRepo.save(GroupMember.builder().group(g2).user(u1).role("OWNER")
                .joinedAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());
        groupMemberRepo.save(GroupMember.builder().group(g2).user(u3).role("MEMBER")
                .joinedAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());

        log.info("[SEED] Group members inserted.");

        // ── Expenses ─────────────────────────────────────────────────────────
        // E1 – G1, paidBy U1, Dinner $120, EQUAL
        Expense e1 = expenseRepo.save(Expense.builder()
                .group(g1).paidBy(u1).description("Dinner")
                .amount(new BigDecimal("120.00"))
                .expenseDate(LocalDate.of(2026, 4, 5)).splitType("EQUAL")
                .createdAt(LocalDateTime.of(2026, 4, 5, 0, 0)).build());

        // E2 – G1, paidBy U1, Taxi $60, UNEQUAL
        Expense e2 = expenseRepo.save(Expense.builder()
                .group(g1).paidBy(u1).description("Taxi")
                .amount(new BigDecimal("60.00"))
                .expenseDate(LocalDate.of(2026, 4, 5)).splitType("UNEQUAL")
                .createdAt(LocalDateTime.of(2026, 4, 5, 0, 0)).build());

        // E3 – G2, paidBy U3, Rent $900, EQUAL
        Expense e3 = expenseRepo.save(Expense.builder()
                .group(g2).paidBy(u3).description("Rent")
                .amount(new BigDecimal("900.00"))
                .expenseDate(LocalDate.of(2026, 4, 1)).splitType("EQUAL")
                .createdAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        log.info("[SEED] Expenses: {}, {}, {}", e1, e2, e3);

        // ── Expense Participants ──────────────────────────────────────────────
        // E1: U1 paid $120, U1+U2+U3 each owe $40
        participantRepo.save(ep(e1, u1, "40.00", "120.00"));
        participantRepo.save(ep(e1, u2, "40.00", "0.00"));
        participantRepo.save(ep(e1, u3, "40.00", "0.00"));

        // E2: U1 paid $60, U1 owes $20, U2 owes $40
        participantRepo.save(ep(e2, u1, "20.00", "60.00"));
        participantRepo.save(ep(e2, u2, "40.00", "0.00"));

        log.info("[SEED] Expense participants inserted.");

        // ── Balances ─────────────────────────────────────────────────────────
        // B1 – G1: U2 owes U1 $40
        balanceRepo.save(Balance.builder().group(g1).owedBy(u2).owedTo(u1)
                .amount(new BigDecimal("40.00"))
                .updatedAt(LocalDateTime.of(2026, 4, 5, 0, 0)).build());

        // B2 – G1: U3 owes U1 $40
        balanceRepo.save(Balance.builder().group(g1).owedBy(u3).owedTo(u1)
                .amount(new BigDecimal("40.00"))
                .updatedAt(LocalDateTime.of(2026, 4, 5, 0, 0)).build());

        // B3 – G2: U1 owes U3 $450
        balanceRepo.save(Balance.builder().group(g2).owedBy(u1).owedTo(u3)
                .amount(new BigDecimal("450.00"))
                .updatedAt(LocalDateTime.of(2026, 4, 1, 0, 0)).build());

        log.info("[SEED] Balances inserted.");

        // ── Settlements ───────────────────────────────────────────────────────
        // S1 – G1: U2 paid U1 $40
        settlementRepo.save(Settlement.builder().group(g1).payer(u2).payee(u1)
                .amount(new BigDecimal("40.00"))
                .createdAt(LocalDateTime.of(2026, 4, 6, 0, 0)).build());

        // S2 – G2: U1 paid U3 $450
        settlementRepo.save(Settlement.builder().group(g2).payer(u1).payee(u3)
                .amount(new BigDecimal("450.00"))
                .createdAt(LocalDateTime.of(2026, 4, 2, 0, 0)).build());

        log.info("[SEED] Settlements inserted.");

        // ── Activity Logs ─────────────────────────────────────────────────────
        activityLogRepo.save(ActivityLog.builder()
                .actorUser(u1).group(g1).expense(e1)
                .action("EXPENSE_ADDED").details("Added Dinner $120.00")
                .createdAt(LocalDateTime.of(2026, 4, 5, 0, 0)).build());

        activityLogRepo.save(ActivityLog.builder()
                .actorUser(u2).group(g1)
                .action("SETTLEMENT_MADE").details("Paid U1 for $40.00")
                .createdAt(LocalDateTime.of(2026, 4, 6, 0, 0)).build());

        activityLogRepo.save(ActivityLog.builder()
                .actorUser(u1).group(g2)
                .action("GROUP_CREATED").details("Created Roommates group")
                .createdAt(LocalDateTime.of(2026, 4, 3, 0, 0)).build());

        log.info("[SEED] Activity logs inserted.");
        log.info("=== [DataInitializer] Sample data ready ===");
    }

    private ExpenseParticipant ep(Expense e, User u, String share, String paid) {
        return ExpenseParticipant.builder()
                .expense(e).user(u)
                .shareAmount(new BigDecimal(share))
                .paidAmount(new BigDecimal(paid))
                .build();
    }
}
