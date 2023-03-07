package com.nunugram.nunugram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String mainPage() {
        return "pages/main/mainPage";
    }

    @GetMapping(value = "/rank")
    public String rankPage() {
        return "pages/main/rankPage";
    }

    @GetMapping(value = "/fish")
    public String cameraPage() {
        return "pages/main/fishPage";
    }

    @GetMapping(value = "/map")
    public String tempPage() {
        return "pages/main/mapPage";
    }


    @GetMapping(value = "/mypage")
    public String myPage() {
        return "pages/main/mypage";
    }

}
