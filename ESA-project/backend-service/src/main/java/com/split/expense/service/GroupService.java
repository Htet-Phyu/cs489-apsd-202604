package com.split.expense.service;

import com.split.expense.entity.Group;
import com.split.expense.entity.GroupMember;
import com.split.expense.entity.User;
import com.split.expense.repository.GroupMemberRepository;
import com.split.expense.repository.GroupRepository;
import com.split.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final UserRepository userRepository;

    @Transactional
    public Group createGroup(String name, UUID createdByUserId) {
        User creator = userRepository.findById(createdByUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + createdByUserId));

        Group group = groupRepository.save(Group.builder().name(name).createdBy(creator).build());

        // Creator is automatically an OWNER member
        groupMemberRepository.save(GroupMember.builder()
                .group(group).user(creator).role("OWNER").build());

        return group;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public GroupMember addMember(UUID groupId, UUID userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        if (groupMemberRepository.existsByGroupAndUser(group, user)) {
            throw new IllegalArgumentException("User is already a member of this group.");
        }

        return groupMemberRepository.save(GroupMember.builder()
                .group(group).user(user).role("MEMBER").build());
    }

    public List<GroupMember> getMembers(UUID groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found: " + groupId));
        return groupMemberRepository.findByGroup(group);
    }
}
