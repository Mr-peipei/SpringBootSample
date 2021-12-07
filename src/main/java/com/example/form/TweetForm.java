package com.example.form;

import com.example.domain.user.model.TweetKey;
import lombok.Data;

@Data
public class TweetForm {
    private TweetKey tweetKey;
    private String tweet;
}
