package com.example.repository;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.FollowKey;
import com.example.domain.user.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, String> {
    /** フォローデータ取得 **/
    @Query("select follow"
            + " from Follow follow"
            + " where follow.followKey.userId = :userId")
    public List<Follow> findFollowsBy(@Param("userId") String userId);

    /** フォローデータ取得(String) **/
    @Query("select follow.follow"
            + " from Follow follow"
            + " where follow.followKey.userId = :userId")
    public List<String> strfindFollows(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("delete from" +
            " Follow follow" +
            " where follow.followKey.userId = :userId and follow.follow = :follow")
    void deleteFollow(String userId, String follow);
}
