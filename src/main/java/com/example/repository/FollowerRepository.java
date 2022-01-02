package com.example.repository;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, String> {
    /** フォロワーデータ取得 **/
    @Query("select follower"
            + " from Follower follower"
            + " where follower.followerKey.userId = :userId")
    public List<Follower> findFollowersBy(@Param("userId") String userId);
}
