package com.split.expense.repository;

import com.split.expense.entity.Group;
import com.split.expense.entity.GroupMember;
import com.split.expense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, UUID> {
    List<GroupMember> findByGroup(Group group);
    List<GroupMember> findByUser(User user);
    boolean existsByGroupAndUser(Group group, User user);
}
