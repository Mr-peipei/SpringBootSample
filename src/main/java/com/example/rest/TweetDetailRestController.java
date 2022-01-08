package com.example.rest;


import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TweetDetailRestController {

    @Autowired
    private TweetService tweetService;

    /*ツイート詳細を表示*/
    @GetMapping("/sample")
    @ResponseBody
    public Tweet getTweetRest(@PathVariable("userId") String userId,
                               @PathVariable("tweetId") String tweetId){

        Tweet tweet = tweetService.findTweetOne("1");
        return tweet;
    }
}
