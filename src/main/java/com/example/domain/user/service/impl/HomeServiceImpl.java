package com.example.domain.user.service.impl;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.HomeService;
import com.example.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private TweetRepository repository;

    @Override
    public List<Tweet> getAllTweet(){
        return repository.findAll(Sort.by(Sort.Direction.DESC, "TweetKey.tweetDate"));
    }

    @Override
    public List<Tweet> getFollowTweet(String userId){
        return repository.findTweetByfollow(userId);
    }
}
