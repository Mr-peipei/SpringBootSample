package com.example.domain.user.service.impl;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    /**ユーザー登録 */
    @Override
    public void signup(MUser user){
        user.setDepartmentId(1);
        user.setRole("ROLE_GENERAL");
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
        mapper.updateOne(userId, password, userName);
    }

    /**ユーザー削除 */
    @Override
    public void deleteUserOne(String userId){
        int count = mapper.deleteOne(userId);
    }
}