package com.example.domain.user.service.impl;

import com.example.domain.user.model.Follower;
import com.example.domain.user.model.FollowerKey;
import com.example.domain.user.service.FollowerService;
import com.example.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    private FollowerRepository repository;

    @Override
    public List<Follower> findFollower(String userId){
        return repository.findFollowersBy(userId);
    }

    @Override
    public void addFollower(Follower follower){
        repository.save(follower);
    }

    @Override
    public void deleteFollower(String userId, String follower){
        repository.deleteFollower(userId, follower);
    }
}
