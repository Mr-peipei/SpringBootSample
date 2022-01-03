package com.example.controller;


import com.example.domain.user.model.*;
import com.example.domain.user.service.FollowService;
import com.example.domain.user.service.FollowerService;
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
import java.util.List;
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
    private FollowerService followerService;

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

        //フォローを取得 ※フォローステータスのため
        List<String> following = followService.strfindFollows(authId);

        //フォロー、フォロワーを取得 ※フォローカウントのため
        List<String> userfollowing = followService.strfindFollows(userId);
        Integer Intfollow = userfollowing.size();
        List<String> userfollower = followerService.strfindFollower(userId);
        Integer Intfollower = userfollower.size();

        //Modelに登録
        model.addAttribute("userProfileForm", form);
        model.addAttribute("authId", authId);
        model.addAttribute("following", following);
        model.addAttribute("Intfollow", Intfollow);
        model.addAttribute("Intfollower", Intfollower);

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

        //フォロワーを登録
        Follower follower = new Follower();
        follower.setFollower(authName);
        FollowerKey followerKey = new FollowerKey();
        followerKey.setFollowerDate(date);
        followerKey.setUserId(followName);
        follower.setFollowerKey(followerKey);

        //フォロー
        followService.following(follow);
        followerService.addFollower(follower);

        return "redirect:/user/{userId}";
    }

    //フォロー解除
    @GetMapping("user/{userId}/unfollow")
    public String unfollow(@PathVariable String userId
            , Model model, UserProfileForm userProfileForm){

        //ログインユーザーを取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();

        //フォロー者を取得
        String followName = userProfileForm.getUserId();

        //フォロー解除
        //フォロー者を解除　①ログイン者のフォロー枠から削除
        //対象のフォロワー者を解除　②フォロー解除者のフォロワーからユーザーを削除
        followService.unfollow(authName, followName);
        followerService.deleteFollower(followName, authName);

        return "redirect:/user/{userId}";
    }
}
