package com.kj.WhatShouldIEatTodayBack.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member extends BaseEntity{

    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String userEmail;

    @Setter
    @Column(nullable = false)
    private String userPw;

//    @Setter
//    @Column(nullable = false, length = 50)
//    @Enumerated(EnumType.STRING)
//    private
}
