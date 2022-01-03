package com.example.domain.user.service;

import com.example.domain.user.model.Follower;

import java.util.List;

public interface FollowerService {

    public List<Follower> findFollower(String userId);

    void addFollower(Follower follower);
}
