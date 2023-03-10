package com.nunukang.nunukang.controller.accounts;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDtoService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class AccountsApiController {

    private final UserService userService;
    private final UserProfileImageDtoService upidService;


    @GetMapping("/user/email/exist")
    public boolean existsCheckByUserEmail(@RequestParam("email") String email) {
        return userService.existsByEmail(email);

    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody User user) {
        if (!userService.existsByEmail(user.getEmail())) {
            return userService.signup(user);
        } else {
            return false;
        }
    }

    @PutMapping("/mypage/profile")
    public boolean profile(@RequestPart(value = "user") User user,
                            @ModelAttribute UserProfileImageDto upid) throws IOException {

        return upidService.updateUserProfile(user, upid);
    }
}
