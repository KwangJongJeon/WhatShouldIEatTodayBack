package com.kj.WhatShouldIEatTodayBack.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Table(name = "Member")
@Entity
public class Member extends BaseEntity{

    @Setter
    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;

    @Setter
    @Column(nullable = false)
    private String memberPw;

    @Builder
    public Member(String memberEmail, String memberPw) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
    }

//    @Setter
//    @Column(nullable = false, length = 50)
//    @Enumerated(EnumType.STRING)
//    private
}
