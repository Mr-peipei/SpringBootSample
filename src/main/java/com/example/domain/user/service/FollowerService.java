package com.example.domain.user.service;

import com.example.domain.user.model.Follower;
import com.example.domain.user.model.FollowerKey;

import java.util.List;

public interface FollowerService {

    public List<Follower> findFollower(String userId);

    void addFollower(Follower follower);

    void deleteFollower(String userId, String follower);
}
