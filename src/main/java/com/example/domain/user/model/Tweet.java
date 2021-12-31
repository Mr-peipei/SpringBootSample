package com.example.domain.user.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "a_tweet")
public class Tweet implements Comparable<Tweet>{
    @EmbeddedId
    private TweetKey tweetKey;
    private String tweet;
    private String tweetId;

    @Override
    public int compareTo(Tweet tweet){
        return tweet.tweetKey.getTweetDate().compareTo(tweetKey.getTweetDate());
    }
}
