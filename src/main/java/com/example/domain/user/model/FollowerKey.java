package com.example.domain.user.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class FollowerKey implements Serializable {
    private String userId;
    private Date followerDate;
}
