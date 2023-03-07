package com.nunukang.nunukang.domain.alert;

import com.nunukang.nunukang.domain.user.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public abstract class Alert {

    @Id @GeneratedValue
    @Column(name = "alert_id")
    private Long id;

    @ManyToOne
    private User alert;

    private Boolean isRead = false;

}
