package com.example.repository;

import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TweetRepository extends JpaRepository<Tweet, String> {
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
