package com.example.form;

import com.example.domain.user.model.Department;
import com.example.domain.user.model.Salary;
import com.example.domain.user.model.Tweet;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserProfileForm {
    private String userId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private String introduction;
    private Integer follows;
    private Integer follower;
    private Department department;
    private List<Tweet> tweetList;
}
