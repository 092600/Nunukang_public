package com.nunukang.nunukang.domain.alert.type;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.nunukang.nunukang.domain.post.Post;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("L")
@NoArgsConstructor
public class PostLikeAlert extends Alert{

    private String message;
    

    public PostLikeAlert(Post post, User user) {
        this.setAlertingUser(post.getPostWriter());
        this.setMakeAlertUser(user);
        this.message = getAlertMessage(post, user);
    }

    public String getAlertMessage(Post post, User user) {
        return user.getName() + " 님께서 " + post.getPostWriter().getName() + " 님의 " +post.getTitle() + " 글에 좋아요를 눌렀습니다.";
    }


}
