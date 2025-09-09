package com.example.boardpjt.service;

import com.example.boardpjt.model.entity.UserAccount;
import com.example.boardpjt.model.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final UserAccountRepository userAccountRepository;
    // UserAccout에 follow, unfollow, following, followers 등등의 기능을 내포

    // 4개

    @Transactional
    public void followUser(String followerUsername, Long targetId) {
        // follwerUsername -> Authentication에 있는 username
        UserAccount follower = userAccountRepository // 내가 없음
                .findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        UserAccount target = userAccountRepository // 팔로우하려는 상대가 없음
                .findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("대상 없음"));
        if (follower.equals(target)) { // 애초에 나 자신이 안 뜨게 할 거지만 혹여나~ 하는 마음에 만든 로직
            throw new IllegalArgumentException("자기 자신을 팔로우 할 수 없음");
        }
    }

    @Transactional
    public void unfollowUser(String followerUsername, Long targetId) {
        // follwerUsername -> Authentication에 있는 username
        UserAccount follower = userAccountRepository // 내가 없음
                .findByUsername(followerUsername)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));
        UserAccount target = userAccountRepository // 팔로우하려는 상대가 없음
                .findById(targetId)
                .orElseThrow(() -> new IllegalArgumentException("대상 없음"));
    }

}
