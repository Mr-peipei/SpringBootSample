package com.example.controller;


import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.TweetForm;
import com.example.form.UserDetailForm;
import com.example.form.UserProfileForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.nio.file.Path;
import java.util.Date;
import java.util.Locale;

@Controller
public class MyProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("user/{userId:.*}")
    public String getUser(UserProfileForm form, Model model,
                          @PathVariable("userId") String userId){

        //ログインユーザー取得
        MUser user = userService.getLoginUserTweet(userId);
        user.setPassword(null);

        form = modelMapper.map(user, UserProfileForm.class);
        form.setTweetList(user.getTweetList());

        //Modelに登録
        model.addAttribute("userProfileForm", form);

        //プロフィール画面の表示
        return "profile/profile";
    }

    @PostMapping("user/tweet")
    public String postTweet(Model model, Locale locale,
               @ModelAttribute @Validated({GroupOrder.class}) TweetForm form,
               @PathVariable("userId") String userId){

        //formをTweetクラスに変換
        model.addAttribute("tweetForm", form);
        Tweet tweetone = modelMapper.map(form, Tweet.class);
        TweetKey tweetKey = new TweetKey();
        tweetKey.setUserId("system@co.jp");
        tweetone.setTweetKey(tweetKey);
        userService.tweeting(tweetone);

        return "user/{userId:.*}";
    }
}
