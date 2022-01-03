package com.example.repository;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.Follower;
import com.example.domain.user.model.FollowerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, String> {
    /** フォロワーデータ取得 **/
    @Query("select follower"
            + " from Follower follower"
            + " where follower.followerKey.userId = :userId")
    public List<Follower> findFollowersBy(@Param("userId") String userId);

    /** フォロワーデータ取得(String) **/
    @Query("select follower.follower"
            + " from Follower follower"
            + " where follower.followerKey.userId = :userId")
    public List<String> strfindFollowers(@Param("userId") String userId);

    @Transactional
    @Modifying
    @Query("delete from" +
            " Follower follower" +
            " where follower.followerKey.userId = :userId and follower.follower = :follower")
    void deleteFollower(String userId, String follower);
}
