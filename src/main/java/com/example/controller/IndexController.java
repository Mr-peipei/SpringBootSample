package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /** 最初のページを表示 */
    @GetMapping("/")
    public String getIndex(){ return"index"; }
}
