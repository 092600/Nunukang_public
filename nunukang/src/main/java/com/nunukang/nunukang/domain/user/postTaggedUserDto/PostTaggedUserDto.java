package com.nunukang.nunukang.domain.user.postTaggedUserDto;


import com.nunukang.nunukang.domain.user.User;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostTaggedUserDto {
    
    private List<Long> postTaggedUsers = new ArrayList<Long>();
}
