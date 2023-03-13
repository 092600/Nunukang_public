package com.nunukang.nunukang.domain.user.imagesDto;

import org.springframework.stereotype.Service;
import com.nunukang.nunukang.domain.user.User;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.transaction.Transactional;

import com.nunukang.nunukang.domain.user.UserService;

@Service
@RequiredArgsConstructor
public class UserProfileImageDtoService {
    private final UserService userService;
 

    @Value("${userProfileImgPath}")
    // userProfileImgPath: /Users/sim/Nunukang/user/profile/
    private String userProfileImgPath;

    @Value("${userBackgroundImgPath}")
    // userBackgroundImgPath: /Users/sim/Nunukang/user/background/
    private String userBackgroundImgPath;



    @Transactional
    public boolean updateUserProfile(User tmp, UserProfileImageDto upid) throws IOException {
        try {
            User user = userService.findById(tmp.getId()).get();

            if (upid.getProfileImage() != null) {
                System.out.println("pr");
                saveProfileImage(user, upid);
            }
    
            if (upid.getBackgroundImage() != null) {
                System.out.println("bk");
                saveBackgroundImage(user, upid);    
            }
    
            userService.updateUserProfileIntro(user,tmp.getProfile().getIntro());

            return true;

        } catch (Exception e) {
            return false;
        }
    }




    public void saveProfileImage(User user, UserProfileImageDto upid) throws IOException {
        String profilePath = createProfileDirectory(userProfileImgPath, user);

        profilePath += "/profile.jpg";

        // 이미지 저장
        Path imgPath = Paths.get(profilePath);
        upid.getProfileImage().transferTo(imgPath);

        userService.updateUserProfileImgPath(user);
    }

    public void saveBackgroundImage(User user, UserProfileImageDto upid) throws IOException {
        String backgroundPath = createBackgroundDirectory(userBackgroundImgPath, user);
    
        backgroundPath += "/background.jpg";

        // 이미지 저장
        Path imgPath = Paths.get(backgroundPath);
        upid.getBackgroundImage().transferTo(imgPath);

        userService.updateUserBackgroundImgPath(user);
    }




    public String createProfileDirectory(String userProfilePath, User user) {
        File userIdDirectory = new File(userProfilePath + user.getId());

        if (!userIdDirectory.exists()) {
            userIdDirectory.mkdir();
    
            return userIdDirectory.getPath();
        } else {
            return userProfilePath + user.getId();
        }
    }


    public String createBackgroundDirectory(String userBackgroundPath, User user) {
        File userIdDirectory = new File(userBackgroundPath + user.getId());

        if (!userIdDirectory.exists()) {
            userIdDirectory.mkdir();
            
            return userIdDirectory.getPath();
            
        } else {
            return userBackgroundPath + user.getId();
        }
    }
}
