package com.nunukang.nunukang.domain.user.profile;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.nunukang.nunukang.domain.user.imagesDto.UserProfileImageDto;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class Profile {

    private String intro;
    private String profileImg = "/userProfileImgPath/default/defaultProfile.JPG";
    private String backgroundImg = "/userBackgroundImgPath/default/defaultBackground.JPG";

}
