package com.example.controller;


import com.example.domain.user.model.*;
import com.example.domain.user.service.FollowService;
import com.example.domain.user.service.TweetService;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.TweetForm;
import com.example.form.UserProfileForm;
import groovy.util.logging.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Controller
public class MyProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private FollowService followService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("user/{userId:.*}")
    public String getUser(UserProfileForm form, TweetForm tweetForm, Model model,
                          @PathVariable("userId") String userId){

        //表示ユーザー取得
        MUser user = userService.getLoginUserTweet(userId);
        user.setPassword(null);
        form = modelMapper.map(user, UserProfileForm.class);

        //ログインユーザー取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authId = auth.getName();

        //ツイート順に並び替え
        Collections.sort(user.getTweetList());
        form.setTweetList(user.getTweetList());

        //Modelに登録
        model.addAttribute("userProfileForm", form);
        model.addAttribute("authId", authId);

        //プロフィール画面の表示
        return "profile/profile";
    }

    //ツイートボタン押下
    //ModelAttributeはmodel.addAttributeを自動でやってくれる。
    @PostMapping("/tweet")
    public String postTweet(@RequestParam("tweet") String tweetStr,
               @ModelAttribute @Validated({GroupOrder.class}) TweetForm form){


        //formをTweetクラスに変換
        Tweet tweetone = modelMapper.map(form, Tweet.class);
        TweetKey tweetKey = new TweetKey();

        //ログインユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        //ツイート日を取得
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        //ユーザーとツイート日を設定する
        tweetKey.setUserId(userId);
        tweetKey.setTweetDate(date);
        tweetone.setTweetId(UUID.randomUUID().toString());
        //ツイートキーを登録
        tweetone.setTweetKey(tweetKey);
        //ツイートをtweetoneにセット
        tweetone.setTweet(tweetStr);

        //ツイート
        tweetService.tweeting(tweetone);

        return "redirect:home/";
    }

    //フォロー
//    @PostMapping(value="/user/detail", params = "follow")
    @GetMapping("user/{userId}/follow")
    public String follow(@PathVariable String userId
            , Model model, UserProfileForm userProfileForm){

        //ログインユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();

        //フォロー日を取得
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        //フォロー者を取得
        String followName = userProfileForm.getUserId();

        //フォロー者を設定 ※リファクタリングは後ほど
        Follow follow = new Follow();
        follow.setFollow(followName);
        FollowKey followKey = new FollowKey();
        followKey.setFollowDate(date);
        followKey.setUserId(authName);
        follow.setFollowKey(followKey);

        //フォロー
        followService.following(follow);

        return "redirect:/user/{userId}";
    }
}
