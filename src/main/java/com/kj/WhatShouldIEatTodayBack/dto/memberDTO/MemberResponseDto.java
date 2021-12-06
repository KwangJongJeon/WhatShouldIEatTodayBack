package com.kj.WhatShouldIEatTodayBack.dto.memberDTO;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {
    Long memberId;
    String memberEmail;
    String memberName;
    String nickName;
    String phoneNumber;

    private MemberResponseDto() {}

    public MemberResponseDto(Member member) {
        memberId = member.getMemberId();
        memberEmail = member.getMemberEmail();
        memberName = member.getName();
        if(member.getNickName() == null) {
            nickName = member.getMemberEmail();
        } else {
            nickName = member.getNickName();
        }

        phoneNumber = toStringPhone(member.getPhone1(), member.getPhone2(), member.getPhone3());
    }

    private String toStringPhone(String phone1, String phone2, String phone3) {
        return phone1 + phone2 + phone3;
    }
}
