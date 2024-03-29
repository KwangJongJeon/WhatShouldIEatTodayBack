package com.kj.WhatShouldIEatTodayBack.dto.memberDTO;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCheckResponseDto {
    Long memberId;
    String memberEmail;
    String nickName;

    public MemberCheckResponseDto(Member member) {
        memberId = member.getId();
        memberEmail = member.getMemberEmail();
        nickName = member.getNickName();
    }
}
