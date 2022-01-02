package com.example.controller;

import com.example.domain.user.model.Follower;
import com.example.domain.user.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FollowerController {

    @Autowired
    private FollowerService followerService;

    @GetMapping("user/{userId}/follower")
    public String getFollower(Model model,
                            @PathVariable("userId") String userId){

        List<Follower> follower = followerService.findFollower(userId);

        model.addAttribute("follower", follower);

        return "follow/follower";
    }
}
