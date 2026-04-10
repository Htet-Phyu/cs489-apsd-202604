package com.split.expense.service;

import com.split.expense.entity.Friend;
import com.split.expense.entity.User;
import com.split.expense.repository.FriendRepository;
import com.split.expense.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public Friend addFriend(UUID ownerUserId, UUID friendUserId, String name, String email, String phone) {
        User owner = userRepository.findById(ownerUserId)
                .orElseThrow(() -> new IllegalArgumentException("Owner user not found: " + ownerUserId));

        User friendUser = null;
        if (friendUserId != null) {
            friendUser = userRepository.findById(friendUserId)
                    .orElseThrow(() -> new IllegalArgumentException("Friend user not found: " + friendUserId));
            if (friendRepository.existsByOwnerUserAndFriendUser(owner, friendUser)) {
                throw new IllegalArgumentException("Friend relationship already exists.");
            }
        }

        return friendRepository.save(Friend.builder()
                .ownerUser(owner)
                .friendUser(friendUser)
                .name(name)
                .email(email)
                .phone(phone)
                .build());
    }

    public List<Friend> getFriendsOf(UUID ownerUserId) {
        User owner = userRepository.findById(ownerUserId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + ownerUserId));
        return friendRepository.findByOwnerUser(owner);
    }

    @Transactional
    public void removeFriend(UUID friendId) {
        if (!friendRepository.existsById(friendId)) {
            throw new IllegalArgumentException("Friend entry not found: " + friendId);
        }
        friendRepository.deleteById(friendId);
    }
}
