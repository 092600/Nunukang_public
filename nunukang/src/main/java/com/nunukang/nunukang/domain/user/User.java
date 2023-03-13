package com.nunukang.nunukang.domain.user;

import java.util.List;

import javax.persistence.*;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.comment.Comment;
import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.user.profile.Profile;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Setter
@Getter
@Entity
public class User {

    @Id @GeneratedValue(generator = "userSequenceGenerator")
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @Embedded
    private Profile profile;

    @Enumerated
    private UserRole role = UserRole.User;



    @OneToMany(mappedBy = "postWriter")
    private List<Post> posts;



    @OneToMany(mappedBy = "alert")
    private List<Alert> alerts;




    @OneToMany(mappedBy = "user", targetEntity = Comment.class)
    private List<Comment> comments;





    @ManyToOne
    @JoinColumn
    private User userFollowing = this;

    @ManyToOne
    @JoinColumn
    private User userFollower = this;

    @OneToMany(mappedBy = "userFollowing")
    private List<User> followingList = new ArrayList<User>();

    @OneToMany(mappedBy = "userFollower")
    private List<User> followerList = new ArrayList<User>();


    
    //  연관관계 편의 메서드
    public void following(User following) {
        this.followingList.add(following);

        if(!following.getFollowerList().contains(this)) {
            following.getFollowerList().add(this);
        }

        if(!following.getUserFollower().getFollowerList().contains(this)) {
            following.getUserFollower().getFollowerList().add(this);
        }
    }

    public void addFollower(User follower) {
        this.followerList.add(follower);

        if(follower.getFollowingList().contains(this)) {
            follower.getFollowingList().add(this);
        }
        //연관관계의 주인을 통한 확인
        if(!follower.getUserFollowing().getFollowingList().contains(this)) {
            follower.getUserFollowing().getFollowingList().add(this);
        }
    }


}
