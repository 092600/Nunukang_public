package com.nunukang.nunukang.domain.post;

import com.nunukang.nunukang.domain.post.images.PostImage;
import com.nunukang.nunukang.domain.post.time.PostTimeEntity;
import com.nunukang.nunukang.domain.comment.Comment;
import com.nunukang.nunukang.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Entity
public class Post extends PostTimeEntity {

    @Id @GeneratedValue(generator = "postSequenceGenerator")
    @Column(name = "post_id")
    private Long id;

    private Long viewCnt = 0L;

    private String title;
    private String content;

    @ManyToOne
    private User postWriter;

    @ManyToMany
    @JoinColumn(name = "like_post")
    private List<User> likers = new ArrayList<User>();


    @OneToMany(mappedBy = "post", targetEntity = Comment.class)
    private List<Comment> comments;

    @OneToMany
    private List<PostImage> images = new ArrayList<PostImage>();

    public void viewPost() {
        this.viewCnt += 1;
    }

}
