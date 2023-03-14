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
    private Profile profile = new Profile();

    
    @Enumerated
    private UserRole role = UserRole.User;



    @OneToMany(mappedBy = "postWriter")
    private List<Post> posts;



    @OneToMany(mappedBy = "alertingUser")
    private List<Alert> alerts;





    @OneToMany(mappedBy = "user", targetEntity = Comment.class)
    private List<Comment> comments;

    
    @ManyToMany
    private List<User> following = new ArrayList<User>();

    @ManyToMany
    private List<User> followers = new ArrayList<User>();


    @ManyToMany
    private List<Post> taggedPosts = new ArrayList<Post>();

    public void addTaggedPost(Post post) {
        if (!this.getTaggedPosts().contains(post)) {
            this.getTaggedPosts().add(post);
        }
    }
}
