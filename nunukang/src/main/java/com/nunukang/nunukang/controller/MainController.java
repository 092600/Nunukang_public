package com.nunukang.nunukang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "pages/main/mainPage";
    }


    @GetMapping(value = "/mypage")
    public String myPage() {
        return "pages/main/mypage";
    }
    
}
