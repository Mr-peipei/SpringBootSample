package com.example.domain.user.service;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.FollowKey;

import java.util.List;

public interface FollowService {

    public List<Follow> findFollows(String userId);

    public List<String> strfindFollows(String userId);

    public void following(Follow follow);

    public void unfollow(String userId, String follow);
}
