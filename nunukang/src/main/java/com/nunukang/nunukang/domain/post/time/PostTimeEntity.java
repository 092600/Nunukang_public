package com.nunukang.nunukang.domain.post.time;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class PostTimeEntity {
    
    @CreatedDate
    private LocalDate createDate;
}
