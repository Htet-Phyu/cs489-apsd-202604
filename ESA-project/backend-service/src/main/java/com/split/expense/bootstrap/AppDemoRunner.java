package com.split.expense.bootstrap;

import com.split.expense.entity.*;
import com.split.expense.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Demonstrates CRUD operations via the service layer.
 * Runs after DataInitializer (Order 2).
 */
@Slf4j
@Component
@Order(2)
@RequiredArgsConstructor
public class AppDemoRunner implements CommandLineRunner {

    private final UserService userService;
    private final FriendService friendService;
    private final GroupService groupService;
    private final ExpenseService expenseService;
    private final SettlementService settlementService;

    private static final String HR = "─".repeat(60);

    @Override
    public void run(String... args) {
        section("EXPENSE SPLITTING APP  —  CRUD DEMO");

        // ── 1. USER CRUD ─────────────────────────────────────────────────────
        section("1. USER CRUD");

        User carol = userService.createUser("Carol White", "carol@email.com", "11111");
        log.info("[CREATE] {}", carol);

        List<User> users = userService.getAllUsers();
        log.info("[READ  ] All users ({})", users.size());
        users.forEach(u -> log.info("         · {}", u));

        carol = userService.updateUser(carol.getId(), "Carol White-Smith", "22222");
        log.info("[UPDATE] {}", carol);

        userService.getUserByEmail("john@email.com")
                .ifPresent(u -> log.info("[READ  ] By email → {}", u));

        UUID carolId = carol.getId();
        userService.deleteUser(carolId);
        log.info("[DELETE] id={} removed", carolId);
        log.info("[READ  ] Users remaining: {}", userService.getAllUsers().size());

        // ── 2. FRIEND MANAGEMENT ─────────────────────────────────────────────
        section("2. FRIEND MANAGEMENT");

        UUID johnId = userService.getUserByEmail("john@email.com").get().getId();

        Friend dave = friendService.addFriend(johnId, null, "Dave Brown", "dave@email.com", "33333");
        log.info("[CREATE] {}", dave);

        List<Friend> johnFriends = friendService.getFriendsOf(johnId);
        log.info("[READ  ] John's friends ({})", johnFriends.size());
        johnFriends.forEach(f -> log.info("         · {}", f));

        friendService.removeFriend(dave.getId());
        log.info("[DELETE] Friend {} removed", dave.getId());

        // ── 3. GROUP MANAGEMENT ──────────────────────────────────────────────
        section("3. GROUP MANAGEMENT");

        Group weekend = groupService.createGroup("Weekend Trip", johnId);
        log.info("[CREATE] {}", weekend);

        UUID aliceId = userService.getUserByEmail("alice@email.com").get().getId();
        GroupMember aliceMember = groupService.addMember(weekend.getId(), aliceId);
        log.info("[ADD   ] Member → {}", aliceMember);

        List<Group> allGroups = groupService.getAllGroups();
        log.info("[READ  ] All groups ({})", allGroups.size());
        allGroups.forEach(g -> log.info("         · {}", g));

        List<GroupMember> members = groupService.getMembers(weekend.getId());
        log.info("[READ  ] Members of '{}' ({})", weekend.getName(), members.size());
        members.forEach(m -> log.info("         · userId={}, role={}", m.getUser().getId(), m.getRole()));

        // ── 4. EXPENSE MANAGEMENT ────────────────────────────────────────────
        section("4. EXPENSE MANAGEMENT");

        // Equal split: John pays $200 hotel, shared with Alice
        Expense hotel = expenseService.addEqualExpense(
                weekend.getId(), johnId,
                "Hotel stay", new BigDecimal("200.00"),
                LocalDate.now(), List.of(johnId, aliceId));
        log.info("[CREATE] Equal expense → {}", hotel);

        // Unequal split: Alice pays $90 groceries (John $30, Alice $60)
        Expense groceries = expenseService.addUnequalExpense(
                weekend.getId(), aliceId,
                "Groceries", new BigDecimal("90.00"),
                LocalDate.now(),
                Map.of(johnId, new BigDecimal("30.00"),
                       aliceId, new BigDecimal("60.00")));
        log.info("[CREATE] Unequal expense → {}", groceries);

        List<Expense> groupExpenses = expenseService.getExpensesByGroup(weekend.getId());
        log.info("[READ  ] Expenses in '{}' ({})", weekend.getName(), groupExpenses.size());
        groupExpenses.forEach(e -> log.info("         · {}", e));

        // ── 5. BALANCES & SETTLEMENT ─────────────────────────────────────────
        section("5. BALANCES & SETTLEMENT");

        List<Balance> balancesBefore = settlementService.getBalancesForGroup(weekend.getId());
        log.info("[READ  ] Balances before settlement ({})", balancesBefore.size());
        balancesBefore.forEach(b -> log.info("         · {}", b));

        // Alice settles $70 with John
        Settlement s = settlementService.settle(weekend.getId(), aliceId, johnId, new BigDecimal("70.00"));
        log.info("[SETTLE] {}", s);

        List<Balance> balancesAfter = settlementService.getBalancesForGroup(weekend.getId());
        log.info("[READ  ] Balances after settlement ({})", balancesAfter.size());
        balancesAfter.forEach(b -> log.info("         · {}", b));

        // ── 6. SEEDED SAMPLE DATA ────────────────────────────────────────────
        section("6. SEEDED SAMPLE DATA (Trip 2026 & Roommates)");

        groupService.getAllGroups().stream()
                .filter(g -> g.getName().equals("Trip 2026") || g.getName().equals("Roommates"))
                .forEach(g -> {
                    log.info("Group '{}' expenses:", g.getName());
                    expenseService.getExpensesByGroup(g.getId())
                            .forEach(e -> log.info("  expense: {}", e));
                    log.info("Group '{}' balances:", g.getName());
                    settlementService.getBalancesForGroup(g.getId())
                            .forEach(b -> log.info("  balance: {}", b));
                });

        section("DEMO COMPLETE");
    }

    private void section(String title) {
        log.info("");
        log.info(HR);
        log.info("  {}", title);
        log.info(HR);
    }
}
