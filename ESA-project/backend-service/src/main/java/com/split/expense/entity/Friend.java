package com.split.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "friends")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /** The user who owns/added this friend entry. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", nullable = false)
    private User ownerUser;

    /**
     * The friend's linked account – nullable when the friend has no account
     * (e.g. Mike Chen in the sample data).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_user_id")
    private User friendUser;

    /** Denormalised display name (always required). */
    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Friend{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
