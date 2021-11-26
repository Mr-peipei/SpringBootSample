package com.example.domain.user.service;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;

import java.util.List;

public interface UserService {

    /** ユーザー登録 */
    public void signup(MUser user);

    /*ユーザー取得 */
    public List<MUser> getUsers(MUser user);

    /*ユーザー取得(1件) */
    public MUser getUserOne(String userId);

    /*ユーザー更新(1件) */
    public void updateUserOne(String userId,
                              String password,
                              String username);

    /*ユーザー削除(1件) */
    public void deleteUserOne(String userId);

    /*ログインユーザー情報取得 */
    public MUser getLoginUser(String userId);

    /*ログインユーザーツイート情報取得 */
    public MUser getLoginUserTweet(String userId);

    /*ツイートする */
    public void tweeting(Tweet tweetone);
}
