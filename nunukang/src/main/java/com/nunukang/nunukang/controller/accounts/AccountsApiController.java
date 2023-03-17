package com.nunukang.nunukang.controller.accounts;

import com.nunukang.nunukang.domain.user.followDto.FollowDto;
import com.nunukang.nunukang.config.authentication.userDetails.NunukangUserDetails;
import com.nunukang.nunukang.domain.alert.AlertService;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDtoService;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v4/accounts")
@RequiredArgsConstructor
public class AccountsApiController {

    private final AlertService alertService;
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

    @DeleteMapping("/alert/{id}")
    public boolean deleteAlert(@PathVariable("id") Long alert_id, Authentication auth) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User user = nud.getUser();

        System.out.println(alert_id);

        return alertService.deleteAlert(alert_id, user);
    }


    @PostMapping("/user/follow")
    public boolean follow(@RequestBody FollowDto followDto) {

        Optional<User> optionalUser = userService.findById(followDto.getFollowUser().getId());
        Optional<User> optionalMyUser = userService.findById(followDto.getMyUser().getId());

        
        if (optionalUser.isPresent() && optionalMyUser.isPresent()) {
            User user = optionalUser.get();
            User myUser = optionalMyUser.get();

            userService.follow(user, myUser);

            return true;
        } else {

            return false;
        }
    }

    @DeleteMapping("/user/unfollow")
    public boolean unfollow(@RequestBody FollowDto followDto) {

        Optional<User> optionalUser = userService.findById(followDto.getFollowUser().getId());
        Optional<User> optionalMyUser = userService.findById(followDto.getMyUser().getId());

        
        if (optionalUser.isPresent() && optionalMyUser.isPresent()) {
            User user = optionalUser.get();
            User myUser = optionalMyUser.get();

            userService.unfollow(user, myUser);

            return true;
        } else {

            return false;
        }
    }

    @DeleteMapping("/user")
    public boolean deleteUser(@RequestBody User tmp) {
        Optional<User> optionalUser = userService.findById(tmp.getId());

        try {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
    
                if (userService.matchingPassword(user, tmp)) {
                    userService.deleteUser(user);
    
                    return true;
                }
            }

            return false;

        } catch (Exception e) {
            return false;
        }
    }
}
