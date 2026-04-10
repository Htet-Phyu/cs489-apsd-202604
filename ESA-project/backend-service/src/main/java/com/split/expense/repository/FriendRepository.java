package com.split.expense.repository;

import com.split.expense.entity.Friend;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<Friend, UUID> {
    List<Friend> findByOwnerUser(User ownerUser);
    boolean existsByOwnerUserAndFriendUser(User ownerUser, User friendUser);
}
