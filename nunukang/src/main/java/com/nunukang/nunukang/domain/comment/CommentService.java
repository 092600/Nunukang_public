package com.nunukang.nunukang.domain.comment;

import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.PostService;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    

    public boolean saveComment(Comment comment) {
        Optional<Post> optionalPost = postService.findById(comment.getPost().getId());
        Optional<User> optionalUser = userService.findById(comment.getUser().getId());
        
        try {
            if (optionalPost.isPresent()) {

                comment.addUser(optionalUser.get());
                comment.addPost(optionalPost.get());


                commentRepository.save(comment);
                return true;    
            } 
            return false;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
        
    }
}
