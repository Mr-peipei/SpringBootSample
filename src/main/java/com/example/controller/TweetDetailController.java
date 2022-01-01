package com.example.controller;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.TweetService;
import com.example.form.TweetForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        Tweet tweet = tweetService.findTweetOne(tweetId);
        //Tweetをformに変換
        form = modelMapper.map(tweet, TweetForm.class);

        //ログインユーザー取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authId = auth.getName();

        //TweetDetailをModelに登録
        model.addAttribute("tweetDetail", form);
        model.addAttribute("authId", authId);

        return "tweetDetail/tweetDetail";
    }

    /**ツイート削除処理 */
    @PostMapping(value = "/user/tweet", name = "deleteTweet")
    public String deleteTweet(TweetForm form, Model model){

        //ツイート削除
        tweetService.deleteTweetOne(form.getTweetId());

        return "redirect:/home/";
    }
}
