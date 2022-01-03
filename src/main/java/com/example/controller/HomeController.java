package com.example.controller;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.HomeService;
import com.example.form.TweetForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/home")
    public String getHome(@ModelAttribute TweetForm form, Model model){

        //ログインユーザー取得は、HOMEからプロフィールの画面遷移のために必要
        //ログインユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        //formをツイートクラスに変更
        Tweet tweet = modelMapper.map(form, Tweet.class);
        //ツイート一覧を取得
        List<Tweet> tweetList = homeService.getFollowTweet(userId);

        //Modelに登録
        model.addAttribute("userId", userId);
        model.addAttribute("tweetList", tweetList);

        return "home/home";
    }
}
