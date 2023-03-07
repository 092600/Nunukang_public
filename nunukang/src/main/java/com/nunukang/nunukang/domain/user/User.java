package com.nunukang.nunukang.domain.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.post.hashtag.HashTag;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private String email;
    private String password;

    @Enumerated
    private UserRole role = UserRole.User;

    @OneToMany(mappedBy = "post_writer")
    private List<Post> posts = new ArrayList<Post>();


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


    @ManyToMany
    private List<Post> likePosts;

    @OneToMany(mappedBy = "alert")
    private List<Alert> alerts;

    @OneToMany(mappedBy = "fishingUser")
    private List<Fish> fishs;

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
