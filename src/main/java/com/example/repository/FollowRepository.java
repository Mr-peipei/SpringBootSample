package com.example.repository;

import com.example.domain.user.model.Follow;
import com.example.domain.user.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, String> {
    /** フォローデータ取得 **/
    @Query("select follow"
            + " from Follow follow"
            + " where follow.followKey.userId = :userId")
    public List<Follow> findFollowsBy(@Param("userId") String userId);
}
