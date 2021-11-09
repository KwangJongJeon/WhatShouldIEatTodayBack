package com.kj.WhatShouldIEatTodayBack.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, length = 20, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(length = 20)
    private LocalDateTime updatedAt;

    @Setter
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean isEnable = true; // 사용 여부
}
