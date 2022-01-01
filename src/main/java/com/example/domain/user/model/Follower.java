package com.example.domain.user.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "a_follower")
public class Follower {
    @EmbeddedId
    private FollowerKey followerKey;
    private String follower;
}
