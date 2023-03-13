package com.nunukang.nunukang.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDto;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.nunukang.nunukang.domain.user.profile.Profile;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

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


}
