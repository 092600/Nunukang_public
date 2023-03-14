package com.nunukang.nunukang.domain.user.followDto;

import lombok.Getter;
import lombok.Setter;

import com.nunukang.nunukang.domain.user.User;

@Setter
@Getter
public class FollowDto {

    private User followUser;
    private User myUser;

}
