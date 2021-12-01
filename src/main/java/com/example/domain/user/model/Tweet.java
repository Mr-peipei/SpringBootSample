package com.example.domain.user.model;

import lombok.Data;
import org.springframework.core.annotation.Order;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "a_tweet")
public class Tweet implements Comparable<Tweet>{
    @EmbeddedId
    private TweetKey tweetKey;
    private String tweet;

    @Override
    public int compareTo(Tweet tweet){
        return tweet.tweetKey.getTweetDate().compareTo(tweetKey.getTweetDate());
    }
}
