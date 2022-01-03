package com.example.controller;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.FollowKey;
import com.example.domain.user.service.FollowService;
import com.example.form.UserProfileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    //フォロー表示
    @GetMapping("user/{userId}/following")
    public String getFollow(Model model,
                            @PathVariable("userId") String userId){

        List<Follow> following = followService.findFollows(userId);

        model.addAttribute("following", following);

        return "follow/following";
    }
}
