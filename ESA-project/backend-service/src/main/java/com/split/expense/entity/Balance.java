package com.split.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "balances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    /** The user who owes money. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owed_by_user_id", nullable = false)
    private User owedBy;

    /** The user who is owed money. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owed_to_user_id", nullable = false)
    private User owedTo;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Balance{owedBy=" + (owedBy != null ? owedBy.getName() : null)
                + " -> owedTo=" + (owedTo != null ? owedTo.getName() : null)
                + ", amount=" + amount + "}";
    }
}
