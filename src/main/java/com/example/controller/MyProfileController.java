package com.example.controller;


import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import com.example.domain.user.service.TweetService;
import com.example.domain.user.service.UserService;
import com.example.form.GroupOrder;
import com.example.form.TweetForm;
import com.example.form.UserDetailForm;
import com.example.form.UserProfileForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@Slf4j
@Controller
public class MyProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("user/{userId:.*}")
    public String getUser(UserProfileForm form, Model model,
                          @PathVariable("userId") String userId){

        //ログインユーザー取得
        MUser user = userService.getLoginUserTweet(userId);
        user.setPassword(null);

        form = modelMapper.map(user, UserProfileForm.class);

        //ツイート順に並び替え
        Collections.sort(user.getTweetList());

        form.setTweetList(user.getTweetList());

        //Modelに登録
        model.addAttribute("userProfileForm", form);

        //プロフィール画面の表示
        return "profile/profile";
    }

    //ツイートボタン押下
    //ModelAttributeはmodel.addAttributeを自動でやってくれる。
    @PostMapping("/tweet")
    public String postTweet(@RequestParam("tweet") String tweetStr,
               @ModelAttribute @Validated({GroupOrder.class}) TweetForm form){

        log.info("tweeting"+tweetStr);

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

        //ツイートキーを登録
        tweetone.setTweetKey(tweetKey);
        //ツイートをtweetoneにセット
        tweetone.setTweet(tweetStr);

        //ツイート
        tweetService.tweeting(tweetone);

        return "redirect:user/list";
    }
}
