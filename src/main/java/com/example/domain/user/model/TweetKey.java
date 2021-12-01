package com.example.domain.user.model;


import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class TweetKey implements Serializable{
    private String userId;
    private Date tweetDate;
}
