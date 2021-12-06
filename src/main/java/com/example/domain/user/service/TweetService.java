package com.example.domain.user.service;

import com.example.domain.user.model.Tweet;

public interface TweetService {

    public void tweeting(Tweet tweetone);

    /*ツイートを削除(1件) */
    public void deleteTweetOne(String tweetId);
}
