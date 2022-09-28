package com.kj.WhatShouldIEatTodayBack.domain;

import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true, length = 50)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPw;

    @Column(nullable = false)
    private String name;

    // 01x-xxxx-xxxx 중 가장 첫번째 부분을 저장
    // 01012345678, 010-1234-5678 등의 포맷팅을 손쉽게 처리하기 위해서 나눠서 구현했음.
    @Column(nullable = false)
    private String phone1;

    @Column(nullable = false)
    private String phone2;

    @Column(nullable = false)
    private String phone3;


    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Unique
    @Column
    private String nickName;

    @Builder
    public Member(String memberEmail, String memberPw, String name, String nickName,
                  String phone1, String phone2, String phone3, MemberRole memberRole) {
        this.memberEmail = memberEmail;
        this.memberPw = memberPw;
        this.name = name;
        this.nickName = nickName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.memberRole = memberRole;
    }

    public void changePassword(String password) {
        this.memberPw = password;
    }

    public void encodePassword(String encodedPassword) { this.memberPw = encodedPassword; }

    public void changeNickName(String nickName) {
        this.nickName = nickName;
    }

    public void changeMemberRole(MemberRole role) {
        this.memberRole = role;
    }

    public void changePhoneNumber(String phoneNumber) {
        int mid = phoneNumber.length() == 10? 6: 7;
        this.phone1 = phoneNumber.substring(0, 3);
        this.phone2 = phoneNumber.substring(3, mid);
        this.phone3 = phoneNumber.substring(mid);
    }
}
