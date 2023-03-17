package com.nunukang.nunukang.controller;

import java.util.Optional;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.domain.fish.FishService;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.post.PostService;
import com.nunukang.nunukang.domain.alert.AlertService;
import com.nunukang.nunukang.domain.post.images.PostImageService;

import org.springframework.security.core.Authentication;
import com.nunukang.nunukang.config.authentication.userDetails.NunukangUserDetails;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final UserService userService;
    private final FishService fishService;
    private final PostService postService;
    private final AlertService alertService;
    private final PostImageService postImageService;


    @GetMapping(value = "/ranking")
    public String rankPage() {

        return "pages/main/ranking/rankingPage";
    }

    @GetMapping(value = "/pictures")
    public String picturesPage() {
        return "pages/main/pictures/picturesPage";
    }


    @GetMapping(value = "/picture/{id}")
    public String cameraPage(@PathVariable Long id, Model model) {
        Optional<Fish> optionalFish = fishService.findById(id);

        if (optionalFish.isPresent()) {
            model.addAttribute("fish", optionalFish.get());
            return "pages/main/pictures/picturePage";    
        } else {
            return "pages/main/pictures/picturesPage";
        }
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
    public String communityPage(Model model, Authentication auth) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();

        model.addAttribute("user", userService.findById(nud.getUser().getId()).get());
        model.addAttribute("posts", postService.findAllPostsOrderByIdDesc());

        return "pages/main/community/communityPage";
    }

    @GetMapping(value = "/community/post")
    public String postCreatePage(Authentication auth, Model model) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User sessionUser = nud.getUser();

        User user = userService.findById(sessionUser.getId()).get();
        model.addAttribute("followers", user.getFollowers());


        return "pages/main/community/createPostPage";
    }




    @GetMapping(value = "/community/myposts")
    public String myPostsPage(Authentication auth, Model model) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User sessionUser = nud.getUser();

        User user = userService.findById(sessionUser.getId()).get();

        model.addAttribute("posts", postService.findAllPostByUser(user));

        return "pages/main/community/communityPage";
    }




    @GetMapping(value = "/community/post/{id}")
    public String postViewPage(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.findById(id);
        // post.getImages().

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postService.viewPost(post);

            model.addAttribute("post", post);
            model.addAttribute("images", postImageService.findByPostWriter(post));


            return "pages/main/community/postViewPage";
        } else {
            return "pages/main/community/communityPage";
        }
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


    
    @GetMapping(value = "/accounts/mypage")
    public String myPage(Authentication auth, Model model) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User sessionUser = nud.getUser();

        User user = userService.findById(sessionUser.getId()).get();

        model.addAttribute("user", user);
        model.addAttribute("taggedPosts", user.getTaggedPosts());
        model.addAttribute("following", user.getFollowing());
        model.addAttribute("follower", user.getFollowers());

        model.addAttribute("existsNewAlerts", alertService.existsNewAlert(user));

        return "pages/accounts/mypage/myPage";
    }


    @GetMapping(value = "/accounts/alert")
    public String alertPage(Authentication auth, Model model) {

        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User user = nud.getUser();
        List<Alert> alerts = alertService.getReadAlerts(user);
        List<Alert> newAlerts = alertService.getNewAlerts(user);


        model.addAttribute("newAlerts", newAlerts) ;
        model.addAttribute("alerts", alerts) ;

        return "pages/accounts/alert/alertPage";
    }

    @GetMapping(value = "/accounts/mypage/profile")
    public String profilePage(Authentication auth, Model model) {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        User tmp = nud.getUser();
        
        model.addAttribute("user", userService.findById(tmp.getId()).get());

        return "pages/accounts/profile/profilePage";
    }

    @GetMapping(value = "/accounts/user/{id}")
    public String userPage(@PathVariable(value = "id") Long id,
                                Authentication auth, Model model) {
        Optional<User> optionalUser = userService.findById(id);

        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
        Optional<User> optionalSessionUser = userService.findById(nud.getUser().getId());

        if (optionalUser.isPresent() && optionalSessionUser.isPresent()) {
            User user = optionalUser.get();
            User myUser = optionalSessionUser.get();
            
            model.addAttribute("user", user);
            model.addAttribute("myUser", myUser);
            model.addAttribute("following", user.getFollowing());
            model.addAttribute("follower", user.getFollowers());


            return "pages/accounts/userpage/userpage";
        } else {

            return "pages/main/community/communityPage";
        }
    }



    @GetMapping(value = "/accounts/user/{id}/followers")
    public String followerPage(@PathVariable(value = "id") Long id, Model model,
                                Authentication auth) {

        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();

        Optional<User> optionalSessionUser = userService.findById(nud.getUser().getId());
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent() && optionalSessionUser.isPresent()) {
            User user = optionalUser.get();
            User myUser = optionalSessionUser.get();

            model.addAttribute("user", myUser);
            model.addAttribute("followers", user.getFollowers());

            return "pages/accounts/follow/followerPage";

        } else {

            return "redirect:/accounts/mypage";
        }
        
    }


    @GetMapping(value = "/accounts/user/{id}/following")
    public String followingPage(@PathVariable(value = "id") Long id, Model model,
                                Authentication auth)  {
        NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();

        Optional<User> optionalSessionUser = userService.findById(nud.getUser().getId());
        Optional<User> optionalUser = userService.findById(id);

        if (optionalUser.isPresent() && optionalSessionUser.isPresent()) {
            User user = optionalUser.get();
            User myUser = optionalSessionUser.get();

            model.addAttribute("user", myUser);
            model.addAttribute("following", user.getFollowing());

            return "pages/accounts/follow/followPage";

        } else {

            return "redirect:/accounts/mypage";
        }
    }
}
