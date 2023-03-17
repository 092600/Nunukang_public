package com.nunukang.nunukang.domain.alert;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


import com.nunukang.nunukang.domain.alert.type.PostLikeAlert;
import com.nunukang.nunukang.domain.alert.type.PostCreateAlert;
import com.nunukang.nunukang.domain.alert.type.CommentCreatedAlert;
import com.nunukang.nunukang.domain.alert.type.PostTaggedAlert;
import com.nunukang.nunukang.domain.post.Post;
import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.comment.Comment;

import com.nunukang.nunukang.domain.alert.type.FollowAlert;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class AlertService {
    
    private final AlertRepository alertRepository;
    
    public void createLikePostAlert(Post post, User user) {
        Alert alert = (Alert) new PostLikeAlert(post, user);

        alertRepository.save(alert);
    }


    public void createdCommentAlert(Comment comment) {
        Alert alert = (Alert) new CommentCreatedAlert(comment);

        alertRepository.save(alert);
    }

    public void createFollowAlert(User follow, User follower) {
        Alert alert = (Alert) new FollowAlert(follow, follower);

        alertRepository.save(alert);
    }

    public void createPostTaggedAlert(Post post, User user, int taggedUserCnt) {
        Alert alert = (Alert) new PostTaggedAlert(post, user, taggedUserCnt);

        alertRepository.save(alert);
    }


    public void createPostCreateAlert(Post post) {

        for (User follower : post.getPostWriter().getFollowers()) {
            Alert alert = (Alert) new PostCreateAlert(post, follower);

            alertRepository.save(alert);
        }
        
    }

    public boolean deleteAlert(Long alert_id, User user)  {
        Optional<Alert> optionalAlert = alertRepository.findById(alert_id);

        if (optionalAlert.isPresent()) {
            Alert alert = optionalAlert.get();

            if (alert.getAlertingUser().getId() == user.getId()) {
                alertRepository.delete(alert);
            
                return true;
            }

            return false;
        } else {
            return false;
        }

    }

    @Transactional
    public List<Alert> getNewAlerts(User user) {
        List<Alert> alerts = alertRepository.findByAlertingUserAndIsReadFalseOrderByIdDesc(user);

        for (Alert alert : alerts ) {
            alert.setIsRead(true);
        }

        return alerts;
    }

    public List<Alert> getReadAlerts(User user) {

        return alertRepository.findByAlertingUserAndIsReadTrueOrderByIdDesc(user);
    }


    public boolean existsNewAlert(User user) {
        if (alertRepository.findByAlertingUserAndIsReadFalseOrderByIdDesc(user).size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
