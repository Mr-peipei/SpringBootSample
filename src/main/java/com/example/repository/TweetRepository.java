package com.example.repository;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TweetRepository extends JpaRepository<Tweet, String> {
    void deleteByTweetKey(TweetKey tweetkey);

    /** ツイート詳細データ取得 **/
    @Query("select tweet"
    + " from Tweet tweet"
    + " where tweet.tweetId = :tweetId")
    public Tweet findTweetByTweetId(@Param("tweetId") String tweetId);
}
