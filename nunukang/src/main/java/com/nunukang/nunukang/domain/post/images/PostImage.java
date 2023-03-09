package com.nunukang.nunukang.domain.post.images;

import javax.mail.Multipart;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.nunukang.nunukang.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class PostImage {

    @Id @GeneratedValue(generator = "postImageGenerator")
    @Column(name = "image_id")
    private Long id;
    
    private String name;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postWriter;


    private String imageSavePath;


    public PostImage(Post post, String imageName, String postPicturePath) {
        this.name = imageName;
        this.imageSavePath = "/post/images/" + post.getId() + "/Pictures/" + imageName;

        this.postWriter = post;
    }
    
}
