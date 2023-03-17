package com.nunukang.nunukang.domain.alert.type;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Setter
@Getter
@Entity
@DiscriminatorValue("P")
@NoArgsConstructor
public class PostCreateAlert extends Alert{

    private String message;

    public PostCreateAlert(Post post, User follower) {
        this.setAlertingUser(follower);
        this.setMakeAlertUser(post.getPostWriter());
        this.message = getAlertMessage(post, follower);
    }

    public String getAlertMessage(Post post, User follower) {
        
        return post.getPostWriter().getName() + " 님께서 " + post.getTitle() + "글을 작성하였습니다.";
    }
}
