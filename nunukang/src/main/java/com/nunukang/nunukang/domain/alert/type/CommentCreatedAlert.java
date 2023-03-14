package com.nunukang.nunukang.domain.alert.type;

import com.nunukang.nunukang.domain.alert.Alert;
import com.nunukang.nunukang.domain.user.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.comment.Comment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
@DiscriminatorValue("C")
@NoArgsConstructor
public class CommentCreatedAlert extends Alert{

    private String message;

    public CommentCreatedAlert(Comment comment) {
        this.setAlertingUser(comment.getPost().getPostWriter());
        this.setMakeAlertUser(comment.getUser());
        this.message = getAlertMessage(comment);
    }

    public String getAlertMessage(Comment comment) {
        Post post = comment.getPost();

        return comment.getUser().getName() + " 님께서 " + post.getPostWriter().getName() + " 님의 " +post.getTitle() + " 글에 \"" +comment.getContent() + "\" 댓글을 남겼습니다.";
    }





}
