package com.example.domain.user.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Data
public class FollowKey implements Serializable {
    private String userId;
    private Date followDate;
}
