package com.nunukang.nunukang.domain.alert;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nunukang.nunukang.domain.user.User;


public interface AlertRepository extends JpaRepository<Alert, Long> {
    
    @Query(value = "SELECT a FROM Alert a WHERE a.alertingUser.id = :id ORDER BY a.id DESC")
    List<Alert> getAlerts(@Param(value = "id") Long id);

    // // List<Alert> findByAlertingUserOrderById(User alertingUser);
    // // List<Alert> findByAlertingUserOrderByIdDesc(User alertingUser);
    // List<Alert> findByAlertingUserOrderByIdDesc(User alertingUser);

    List<Alert> findByAlertingUserAndIsReadTrueOrderByIdDesc(User alertingUser);
    List<Alert> findByAlertingUserAndIsReadFalseOrderByIdDesc(User alertingUser);

}
