package com.split.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "expense_participants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseParticipant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expense_id", nullable = false)
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** This participant's share of the expense. */
    @Column(name = "share_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal shareAmount;

    /** Amount this participant actually paid (0 unless they are the payer). */
    @Column(name = "paid_amount", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Override
    public String toString() {
        return "ExpenseParticipant{userId=" + (user != null ? user.getId() : null)
                + ", shareAmount=" + shareAmount + ", paidAmount=" + paidAmount + "}";
    }
}
