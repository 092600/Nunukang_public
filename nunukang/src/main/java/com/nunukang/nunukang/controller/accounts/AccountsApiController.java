package com.nunukang.nunukang.controller.accounts;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class AccountsApiController {

    private final UserService userService;


    @GetMapping("/user/email/exist")
    public boolean existsCheckByUserEmail(@RequestParam("email") String email) {
        return userService.existsByEmail(email);

    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody User user) {
        return userService.signup(user);
    }
}
