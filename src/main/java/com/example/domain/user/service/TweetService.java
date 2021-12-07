package com.example.domain.user.service;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;

public interface TweetService {

    public void tweeting(Tweet tweetone);

    /*ツイートを削除(1件) */
    public void deleteTweetOne(TweetKey tweetKey);

}
