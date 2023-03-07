package com.nunukang.nunukang.domain.post;

import com.nunukang.nunukang.domain.post.hashtag.HashTag;
import com.nunukang.nunukang.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private Long viewCnt = 0L;

    private String title;

    @ManyToOne
    private User post_writer;

    @ManyToMany
    private List<User> likeUser;

    @OneToMany(mappedBy = "postId")
    private List<HashTag> hashTags;
}
