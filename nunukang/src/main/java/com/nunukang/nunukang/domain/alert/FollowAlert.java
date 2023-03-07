package com.nunukang.nunukang.domain.alert;

import com.nunukang.nunukang.domain.user.User;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("F")
public class FollowAlert extends Alert{

    private String content;

}
