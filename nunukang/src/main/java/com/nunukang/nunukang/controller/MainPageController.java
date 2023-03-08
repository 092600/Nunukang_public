package com.nunukang.nunukang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class MainPageController {


    @GetMapping(value = "/ranking")
    public String rankPage() {
        return "pages/main/ranking/rankingPage";
    }

    @GetMapping(value = "/pictures")
    public String cameraPage() {
        return "pages/main/pictures/picturesPage";
    }

    @GetMapping(value = "/")
    public String mainPage() {
        return "pages/main/camera/cameraPage";
    }

    @GetMapping(value = "/map")
    public String tempPage() {
        return "pages/main/map/mapPage";
    }


    @GetMapping(value = "/community")
    public String myPage() {
        return "pages/main/community/communityPage";
    }



    // accounts

    @GetMapping(value = "/accounts/login")
    public String loginPage(@RequestParam(name = "error", required = false) String errorMessage,
        Model model) {

        model.addAttribute("errorMessage", errorMessage);


        return "pages/accounts/login/loginPage";
    }

        // accounts

    @GetMapping(value = "/accounts/signup")
    public String signupPage() {
        return "pages/accounts/signup/signupPage";
    }

}
