package com.nunukang.nunukang.domain.post.like;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import java.util.List;
import com.nunukang.nunukang.domain.user.User;

@Embeddable
public class PostLikeUser {
    
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "post_id")
    private List<User> postLikeUsers;
}
