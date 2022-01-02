package com.example.controller;

import com.example.domain.user.model.Follow;
import com.example.domain.user.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FollowController {

    @Autowired
    private FollowService followService;

    @GetMapping("user/{userId}/following")
    public String getFollow(Model model,
                            @PathVariable("userId") String userId){

        List<Follow> following = followService.findFollows(userId);

        model.addAttribute("following", following);

        return "follow/following";
    }
}
