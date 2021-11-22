package com.example.controller;


import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MyProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("user/{userId:.*}")
    public String getUser(UserDetailForm form, Model model,
                          @PathVariable("userId") String userId){

        //ログインユーザー取得
        MUser user = userService.getLoginUser(userId);
        user.setPassword(null);

        form = modelMapper.map(user, UserDetailForm.class);

        //Modelに登録
        model.addAttribute("userDetailForm", form);

        //プロフィール画面の表示
        return "profile/profile";
    }
}
