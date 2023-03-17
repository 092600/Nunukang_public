package com.nunukang.nunukang.domain.alert.type;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.post.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Setter
@Getter
@Entity
@DiscriminatorValue("T")
@NoArgsConstructor
public class PostTaggedAlert extends Alert{

    private String message;
    

    public PostTaggedAlert(Post post, User user, int taggedUserCnt) {
        this.setAlertingUser(user);
        this.setMakeAlertUser(post.getPostWriter());
        this.message = getAlertMessage(post, user, taggedUserCnt);
    }

    public String getAlertMessage(Post post, User user, int taggedUserCnt) {
        if (taggedUserCnt <= 1) {
            return post.getPostWriter().getName() + "님이 " + post.getTitle() + " 글에서 " + user.getName() + " 님을 " + " 태그하셨습니다.";    
        } else {
            return post.getPostWriter().getName() + "님이 " + post.getTitle() + " 글에서 " + user.getName() + " 님외 " + String.valueOf(taggedUserCnt-1) + " 명을 태그하셨습니다.";    
        }
    }


}