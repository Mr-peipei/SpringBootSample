package com.example.repository;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, String> {
    /**
     * 自分とフォロー者のツイートを取得
     */
    @Query("select tweet from" +
            " Tweet tweet" +
            " where tweet.tweetKey.userId = :userId" +
            " or tweet.tweetKey.userId in" +
            " (select follow.follow from Follow follow" +
            " where follow.followKey.userId = :userId)" +
            " order by tweet.tweetKey.tweetDate desc")
    public List<Tweet> findTweetByfollow(String userId);


    /** ツイードIDをもとにツイートを削除
     * JPAで削除をする際は、@Transactionalアノテーションが必要
     * @param tweetId
     */
    @Transactional
    void deleteByTweetId(String tweetId);

    /** ツイート詳細データ取得 **/
    @Query("select tweet"
    + " from Tweet tweet"
    + " where tweet.tweetId = :tweetId")
    public Tweet findTweetByTweetId(@Param("tweetId") String tweetId);
}
