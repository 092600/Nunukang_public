package com.nunukang.nunukang.domain.alert;

import com.nunukang.nunukang.domain.user.User;
import com.nunukang.nunukang.domain.alert.time.AlertTimeEntity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ALERT_TYPE")
public abstract class Alert extends AlertTimeEntity {

    @Id @GeneratedValue
    @Column(name = "alert_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "alerting_user_id")
    private User alertingUser;

    @ManyToOne
    @JoinColumn(name = "make_alert_user_id")
    private User makeAlertUser;

    private Boolean isRead = false;

}
