package com.nunukang.nunukang.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDto;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.nunukang.nunukang.domain.user.profile.Profile;
import com.nunukang.nunukang.domain.alert.AlertService;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.alert.type.PostTaggedAlert;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {
    
    private final AlertService alertService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean signup(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            
            return false;
        }
    }

    @Transactional
    public void taggingUserToPost(List<Long> userIdList, Post post) {
        User user; Optional<User> optionalUser; 

        for (Long id : userIdList) {
            optionalUser = userRepository.findById(id);

            if (optionalUser.isPresent()) {
                user = optionalUser.get();

                user.addTaggedPost(post);
                
                alertService.createPostTaggedAlert(post, user, userIdList.size());
            }
        }
    }

    @Transactional
    public void updateUserProfileIntro(User user, String tmp) {
        user.getProfile().setIntro(tmp);
    }

    @Transactional
    public void updateUserProfileImgPath(User user) {
        user.getProfile().setProfileImg("/userProfileImgPath/"+user.getId()+"/profile.jpg");
    }

    
    @Transactional
    public void updateUserBackgroundImgPath(User user) {
        user.getProfile().setBackgroundImg("/userBackgroundImgPath/"+user.getId()+"/background.jpg");
    }


    @Transactional
    public boolean follow(User followUser, User followerUser) {
        try {
            if (!followerUser.getFollowing().contains(followUser)) {
                followerUser.getFollowing().add(followUser);
            }
            
            if (!followUser.getFollowers().contains(followerUser)) {
                followUser.getFollowers().add(followerUser);

                alertService.createFollowAlert(followUser, followerUser);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }
    }

    @Transactional
    public boolean unfollow(User followUser, User followerUser) {
        try {
            if (followerUser.getFollowing().contains(followUser)) {
                followerUser.getFollowing().remove(followUser);
            }
            
            if (followUser.getFollowers().contains(followerUser)) {
                followUser.getFollowers().remove(followerUser);
            }

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;
        }
    }

}
