package com.chunjae.jpa1.entity;

import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
public class BaseEntity {
    @CreatedDate // 만들때 시간
    @Column(name="regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate // 제일 최근의 시간
    @Column(name="moddate")
    private LocalDateTime modDate;
}
