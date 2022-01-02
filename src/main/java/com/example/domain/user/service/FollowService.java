package com.example.domain.user.service;

import com.example.domain.user.model.Follow;

import java.util.List;

public interface FollowService {

    public List<Follow> findFollows(String userId);
}
