package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.model.Tweet;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    /**ユーザー登録 */
    @Transactional
    @Override
    public void signup(MUser user){
        //存在チェック
        boolean exists = repository.existsById(user.getUserId());
        if (exists) {
            throw new DataAccessException("ユーザーが既に存在"){};
        }

        user.setDepartmentId(1); //部署
        user.setRole("ROLE_GENERAL"); //ロール

        //パスワード暗号化
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        //insert
        repository.save(user);
    }

    /**ユーザー取得 */
    @Override
    public List<MUser> getUsers(MUser user){

        //検索条件
        ExampleMatcher matcher = ExampleMatcher
                .matching() //and条件
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //like句
                .withIgnoreCase(); //大文字小文字の両方

        return repository.findAll(Example.of(user, matcher));
    }

    /**ユーザー取得(1件) */
    @Transactional
    @Override
    public MUser getUserOne(String userId){
        Optional<MUser> options = repository.findById(userId);
        MUser user = options.orElse(null);
        return user;
    }

    /**ユーザー更新(1件) */
    @Transactional
    @Override
    public void updateUserOne(String userId,
                              String password,
                              String userName){
        //パスワード暗号化
        String encryptPassword = encoder.encode(password);

        //ユーザー更新
        repository.updateUser(userId, encryptPassword, userName);

    }

    /**ユーザー削除 */
    @Override
    public void deleteUserOne(String userId){
        repository.deleteById(userId);
    }

    /*ログインユーザー情報取得*/
    @Override
    public MUser getLoginUser(String userId){
        return repository.findLoginUser(userId);
    }

    @Override
    public MUser getLoginUserTweet(String userId){ return repository.findLoginUserTweet(userId); }

}
