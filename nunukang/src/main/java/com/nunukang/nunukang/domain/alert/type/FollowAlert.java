package com.nunukang.nunukang.domain.alert.type;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
@DiscriminatorValue("F")
@NoArgsConstructor
public class FollowAlert extends Alert{

    private String message;

    public FollowAlert(User follow, User follower) {
        this.setAlertingUser(follow);
        this.setMakeAlertUser(follower);
        this.message = getAlertMessage(follow, follower);
    }

    public String getAlertMessage(User follow, User follower) {
        
        return follower.getName() + " 님께서 " + follow.getName() + " 님을 " + "팔로우하였습니다.";
    }

}
