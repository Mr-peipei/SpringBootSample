package com.example.domain.user.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "m_user")
public class MUser{
    @Id
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Integer departmentId;
    private String role;
    private String introduction;
    private Integer follows;
    private Integer follower;

    @ManyToOne(optional = true)
    @JoinColumn(insertable = false, updatable = false, name = "departmentId")
    private Department department;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name = "userId")
    private List<Salary> salaryList;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name= "userId")
    private List<Tweet> tweetList;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name= "userId")
    private List<Follow> followsList;

    @OneToMany
    @JoinColumn(insertable = false, updatable = false, name= "userId")
    private List<Follower> followersList;
}