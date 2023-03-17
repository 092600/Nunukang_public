package com.nunukang.nunukang.domain.post;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.post.imageDto.PostImagesDto;
import com.nunukang.nunukang.domain.post.imageDto.PostImagesDtoService;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.user.UserService;
import com.nunukang.nunukang.domain.user.postTaggedUserDto.PostTaggedUserDto;
import com.nunukang.nunukang.domain.alert.AlertService;

import lombok.RequiredArgsConstructor;
import java.util.List;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {


    private final UserService userService;
    private final AlertService alertService;
    private final PostRepository postRepository;
    private final PostImagesDtoService postImagesDtoService;

    @Value("${postImagesPath}")
    // /Users/sim/Nunukang/Post/
    String postImagesPath;


    public Long savePost(Post post, PostTaggedUserDto ptud, PostImagesDto postImagesDto) throws IOException {
        Optional<User> optionalUser = userService.findById(post.getPostWriter().getId());

        if (optionalUser.isPresent()) {
            try {
    
                post.setPostWriter(optionalUser.get());
                post = postRepository.save(post);

                // post 생성 알림 > post 작성자의 팔로워들에게 보냄
                alertService.createPostCreateAlert(post);

                // images를 PostImage 객체로 변환해 DB에 INSERT & 로컬 서버에 이미지를 저장
                if (postImagesDto.getImages() != null) {
                    postImagesDtoService.saveImages(post, postImagesDto, postImagesPath);
                }
                
                userService.taggingUserToPost(ptud.getPostTaggedUsers(), post);

                return post.getId();

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                return 0L;
            }
        } else {
            return 0L;
        }
    }


    @Transactional
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void viewPost(Post post) {
        post.viewPost();
    }


    @Transactional
    public boolean likePost(Long id, User tmp) {
        Optional<Post> optionalPost = findById(id);
        Optional<User> optionalUser = userService.findById(tmp.getId());
        try {
            if (optionalPost.isPresent() && optionalUser.isPresent()) {
                Post post = optionalPost.get();
                User user = optionalUser.get();

                if (!post.getPostWriter().equals(user)) {
                    alertService.createLikePostAlert(post, user);
                }
                
                addLikers(post, user);
                
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        
        
    }

    @Transactional
    public boolean unlikePost(Long id, User tmp) {
        Optional<Post> optionalPost = findById(id);
        Optional<User> optionalUser = userService.findById(tmp.getId());

        try {
            if (optionalPost.isPresent() && optionalUser.isPresent()) {
                Post post = optionalPost.get();
                User user = optionalUser.get();

                removeLikers(post, user);
                
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        
    }

    @Transactional
    public void addLikers(Post post, User user) {
        if(!post.getLikers().contains(user)) {    
            post.getLikers().add(user);

        }
    }

    @Transactional
    public void removeLikers(Post post, User user) {
        if (post.getLikers().contains(user)) {
            post.getLikers().remove(user);
        }
    }
    


    public List<Post> findAllPostsOrderByIdDesc() {
        return postRepository.findAllByOrderByIdDesc();
    }

    public List<Post> findAllPostByUser(User user) {
        return postRepository.findByPostWriterOrderByIdDesc(user);
    }


}
