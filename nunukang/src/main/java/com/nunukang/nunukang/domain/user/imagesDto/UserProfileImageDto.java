package com.nunukang.nunukang.domain.user.imagesDto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;



@Setter
@Getter
public class UserProfileImageDto {
    
    private MultipartFile profileImage;
    private MultipartFile backgroundImage;

}
