package com.nunukang.nunukang.domain.post.hashtag;

import com.nunukang.nunukang.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
public class HashTag {

    @Id @GeneratedValue
    private Long id;

    private String hashtag;

    @ManyToOne
    private Post postId;

}
