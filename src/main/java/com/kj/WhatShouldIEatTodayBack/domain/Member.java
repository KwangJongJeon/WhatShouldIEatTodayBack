package com.kj.WhatShouldIEatTodayBack.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity{


    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false)
    private String name;

    // TODO: 핸드폰 번호 구현
//    @Column(nullable = false)
//    private String phoneNumber

    @Column
    private String nickName;



    @Builder
    public Member(String memberEmail, String memberPw, String name) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.name = name;
    }

    //    @Setter
    //    @Column(nullable = false, length = 50)
    //    @Enumerated(EnumType.STRING)
    //    private

    public void changePassword(String password) {
        this.memberPw = password;
    }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }


}
