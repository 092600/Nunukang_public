package com.nunukang.nunukang.controller.community;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.nunukang.nunukang.domain.comment.Comment;
import com.nunukang.nunukang.domain.comment.CommentService;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.PostService;
import com.nunukang.nunukang.domain.post.imageDto.PostImagesDto;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.config.authentication.userDetails.NunukangUserDetails;
import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.alert.type.PostLikeAlert;


import lombok.RequiredArgsConstructor;
import com.nunukang.nunukang.domain.user.postTaggedUserDto.PostTaggedUserDto;

@RestController
@RequestMapping("/api/v4/community")
@RequiredArgsConstructor
public class CommunityAPiController {
    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/post/create")
    public Long savePost(@RequestPart(value = "post") Post post,
                                    @ModelAttribute PostImagesDto imagesDto,
                                    @ModelAttribute PostTaggedUserDto ptud) throws IOException {

        return postService.savePost(post, ptud, imagesDto);
    }

    @PostMapping("/post/{post_id}/comment")
    public boolean saveComment(@RequestBody Comment comment) {

        return commentService.saveComment(comment);
    }


    @PatchMapping("/post/{post_id}/like")
    public boolean likePost(@PathVariable("post_id") Long id, @RequestBody User user) {

        postService.likePost(id, user);

        return true;
    }

    @PatchMapping("/post/{post_id}/unlike")
    public boolean unlikePost(@PathVariable("post_id") Long id, @RequestBody User user) {
        
        return postService.unlikePost(id, user);
    }
    
}
