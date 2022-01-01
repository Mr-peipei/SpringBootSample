package com.example.domain.user.service.impl;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import com.example.domain.user.service.TweetService;
import com.example.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository repository;

    @Override
    public void tweeting(Tweet tweetone){
        repository.save(tweetone);
    };

    @Override
    public void deleteTweetOne(String tweetId){
        repository.deleteByTweetId(tweetId);
    };

    @Override
    public Tweet findTweetOne(String tweetId){
        return repository.findTweetByTweetId(tweetId);
    }
}
