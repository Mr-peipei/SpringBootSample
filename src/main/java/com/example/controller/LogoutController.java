package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class LogoutController {

    /** ログイン画面にリダイレクト */
    public String postLogout(){
        log.info("ログアウト");
        return "redirect:/login";
    }
}
