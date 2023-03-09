package com.nunukang.nunukang.domain.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.comment.time.CommentTimeEntity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment extends CommentTimeEntity {
//  
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;


    public void addPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }

    public void addUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }
}

