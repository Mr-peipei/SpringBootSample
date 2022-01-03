package com.example.domain.user.service.impl;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.FollowKey;
import com.example.domain.user.service.FollowService;
import com.example.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository repository;

    @Override
    public List<Follow> findFollows(String userId){
        return repository.findFollowsBy(userId);
    }

    @Override
    public List<String> strfindFollows(String userId){
        return repository.strfindFollows(userId);
    }

    @Override
    public void following(Follow follow){
        repository.save(follow);
    }

    @Override
    public void unfollow(String userId, String follow){
        repository.deleteFollow(userId, follow);
    }
}
