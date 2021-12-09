package com.example.domain.user.service;

import com.example.domain.user.model.Tweet;
import com.example.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HomeService {

    public List<Tweet> getAllTweet();
}
