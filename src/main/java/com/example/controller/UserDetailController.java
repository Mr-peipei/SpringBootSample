package com.example.controller;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form, Model model,
                          @PathVariable("userId") String userId){

        //ユーザー1件取得
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        //MUserをformに変換
        form = modelMapper.map(user, UserDetailForm.class);
        form.setSalaryList(user.getSalaryList());

        //Modelに登録
        model.addAttribute("userDetailForm", form);

        //ユーザー詳細画面を表示
        return "user/detail";
    }

    /**ユーザー更新処理 */
    @PostMapping(value="/detail", params="update")
    public String updateUser(UserDetailForm form, Model model){

        try{
            //ユーザー更新
            userService.updateUserOne(form.getUserId(),
                    form.getPassword(),
                    form.getUserName());
        }catch (Exception e){
            log.error("ユーザー更新でエラー", e);
        }

        //ユーザー一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    /**ユーザー削除処理 */
    @PostMapping(value = "/detail", params="delete")
    public String deleteUser(UserDetailForm form, Model model){

        //ユーザーを削除
        userService.deleteUserOne(form.getUserId());

        //ユーザー一覧画面にリダイレクト
        return "redirect:/user/list";
    }
}
