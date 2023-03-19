package com.nunukang.nunukang.domain.comment;

import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.PostService;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;

import com.nunukang.nunukang.domain.alert.AlertService;


import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final UserService userService;
    private final PostService postService;
    private final AlertService AlertService;
    private final CommentRepository commentRepository;
    

    public boolean saveComment(Comment comment) {
        Optional<Post> optionalPost = postService.findById(comment.getPost().getId());
        Optional<User> optionalUser = userService.findById(comment.getUser().getId());
        
        try {
            if (optionalPost.isPresent() && optionalUser.isPresent()) {

                comment.addUser(optionalUser.get());
                comment.addPost(optionalPost.get());

                if (!optionalUser.get().equals(optionalPost.get().getPostWriter())) {
                    AlertService.createdCommentAlert(comment);
                }

                commentRepository.save(comment);

            

                return true;    
            } 
            return false;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
        
    }


    public boolean deleteComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            commentRepository.delete(comment.get());
            
            return true;
        } else {
            
            return false;
        }
    }
}
