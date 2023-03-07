package com.nunukang.nunukang.domain.alert;

import com.nunukang.nunukang.domain.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("P")
public class PostCreateAlert extends Alert{

    private String alert;

}
