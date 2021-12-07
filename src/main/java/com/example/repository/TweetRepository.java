package com.example.repository;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface TweetRepository extends JpaRepository<Tweet, String> {
    void deleteByTweetKey(TweetKey tweetkey);
}
