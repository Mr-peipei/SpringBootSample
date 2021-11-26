package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;
import com.example.domain.user.model.TweetKey;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private PasswordEncoder encoder;

    /**ユーザー登録 */
    @Override
    public void signup(MUser user){
        user.setDepartmentId(1); //部署
        user.setRole("ROLE_GENERAL"); //ロール

        //パスワード暗号化
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        mapper.insertOne(user);
    }

    /**ユーザー取得 */
    @Override
    public List<MUser> getUsers(MUser user){
        return mapper.findMany(user);
    }

    /**ユーザー取得(1件) */
    @Transactional
    @Override
    public MUser getUserOne(String userId){
        return mapper.findOne(userId);
    }

    /**ユーザー更新 */
    @Override
    public void updateUserOne(String userId,
                          String password,
                          String userName){

        //パスワード暗号化
        String encryptPassword = encoder.encode(password);
        mapper.updateOne(userId, encryptPassword, userName);
    }

    /**ユーザー削除 */
    @Override
    public void deleteUserOne(String userId){
        int count = mapper.deleteOne(userId);
    }

    /*ログインユーザー情報取得*/
    @Override
    public MUser getLoginUser(String userId){
        return mapper.findLoginUser(userId);
    }

    @Override
    public MUser getLoginUserTweet(String userId){
        return mapper.findLoginUserTweet(userId);
    }

    /**ツイートする */
    @Override
    public void tweeting(Tweet tweetone){
        TweetKey tweetKey = new TweetKey();
        Date date = new Date();
        tweetKey.setTweetDate(date);
        tweetKey.setUserId(tweetone.getTweetKey().getUserId());
        tweetone.setTweetKey(tweetKey);

        mapper.insertTweet(tweetone);
    }

}
