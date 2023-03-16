package com.nunukang.nunukang.domain.user;

import java.util.List;

import javax.persistence.*;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.comment.Comment;
import com.nunukang.nunukang.domain.fish.Fish;
import com.nunukang.nunukang.domain.fishingSpot.FishingSpot;
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
    @JoinTable(name = "user_following", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "following_user_id"))
    private List<User> following = new ArrayList<User>();


    
    @ManyToMany
    @JoinTable(name = "user_followers", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "followers_user_id"))
    private List<User> followers = new ArrayList<User>();


    

    @ManyToMany
    @JoinTable(name = "user_tagged_posts", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "tagged_post_id"))
    private List<Post> taggedPosts = new ArrayList<Post>();




    @ElementCollection
    @CollectionTable(
        name = "favorite_spots_id",
        joinColumns=@JoinColumn(name = "user_id")
    )
    @Column(name = "favorite_spots_id")
    private List<Long> favoriteSpotsId = new ArrayList<Long>();
    

    public void addTaggedPost(Post post) {
        if (!this.getTaggedPosts().contains(post)) {
            this.getTaggedPosts().add(post);
        }
    }

}
