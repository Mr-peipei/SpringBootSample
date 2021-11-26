package com.example.repository;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /*ユーザー登録*/
    public int insertOne(MUser user);

    /*ユーザー取得*/
    public List<MUser> findMany(MUser user);

    /*ユーザー取得*/
    public MUser findOne(String userId);

    /*ユーザー更新*/
    public void updateOne(@Param("userId") String userId,
                          @Param("password") String password,
                          @Param("userName") String userName);

    /*ユーザー削除*/
    public int deleteOne(@Param("userId") String userId);

    /*ログインユーザー取得*/
    public MUser findLoginUser(String userId);

    /*ログインユーザーツイート取得 */
    public MUser findLoginUserTweet(String userId);

    public int insertTweet(Tweet tweet);
}
