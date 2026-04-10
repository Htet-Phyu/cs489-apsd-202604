package com.split.expense.repository;

import com.split.expense.entity.ActivityLog;
import com.split.expense.entity.Group;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, UUID> {
    List<ActivityLog> findByActorUser(User actorUser);
    List<ActivityLog> findByGroupOrderByCreatedAtDesc(Group group);
}
