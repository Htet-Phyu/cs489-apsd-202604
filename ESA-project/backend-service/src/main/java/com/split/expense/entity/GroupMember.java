package com.split.expense.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "group_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupMember {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    /** OWNER or MEMBER */
    @Column(nullable = false)
    private String role;

    @PrePersist
    private void prePersist() {
        if (joinedAt == null) joinedAt = LocalDateTime.now();
        if (role == null) role = "MEMBER";
    }

    @Override
    public String toString() {
        return "GroupMember{groupId=" + (group != null ? group.getId() : null)
                + ", userId=" + (user != null ? user.getId() : null)
                + ", role='" + role + "'}";
    }
}
