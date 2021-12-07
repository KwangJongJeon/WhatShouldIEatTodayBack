package com.kj.WhatShouldIEatTodayBack.dto.memberDTO;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCheckResponseDto {
    Long memberId;
    String memberEmail;
    String nickName;

    public MemberCheckResponseDto(Member member) {
        memberId = member.getMemberId();
        memberEmail = member.getMemberEmail();
        nickName = member.getNickName();
    }
}
