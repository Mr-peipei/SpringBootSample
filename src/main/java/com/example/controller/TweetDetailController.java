package com.example.controller;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.TweetService;
import com.example.form.TweetForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TweetDetailController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/user/{userId}/{tweetId}")
    public String getTweet(TweetForm form, Model model,
                           @PathVariable("userId") String userId,
                           @PathVariable("tweetId") String tweetId){

        //作成途中
        Tweet tweet = tweetService.findTweetOne(tweetId);

//        Tweetをformに変換
        form = modelMapper.map(tweet, TweetForm.class);

//        TweetDetailをModelに登録
        model.addAttribute("tweetDetail", form);

        return "tweetDetail/tweetDetail";
    }
}
