package com.nunukang.nunukang.controller.community;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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


    @DeleteMapping("/post/{id}")
    public Boolean deletePost(@PathVariable("id") Long id, Authentication auth) {
        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            NunukangUserDetails nud = (NunukangUserDetails) auth.getPrincipal();
            if (post.getPostWriter().getId() == nud.getUser().getId()) {
                postService.deletePost(post);

                return true;
            }

            return false;
        } else {
            return false;
        }
    }
    @PostMapping("/post/{post_id}/comment")
    public boolean saveComment(@RequestBody Comment comment) {

        return commentService.saveComment(comment);
    }


    @DeleteMapping("/post/{post_id}/comment/{comment_id}")
    public boolean saveComment(@PathVariable("comment_id") Long commentId) {

        return commentService.deleteComment(commentId);
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
