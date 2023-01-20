package com.kj.WhatShouldIEatTodayBack.service.auth;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import lombok.Data;

/**
 * 유저의 세부 정보까지 포함합니다. (이름, 연락처 등)
 */
@Data
public class MemberInfoDetailDto {
    private String memberEmail;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String authorities;
    private String photosImagePath;

    public void setPhoneNumberFormatted(Member member) {
        StringBuilder sb = new StringBuilder();
        sb.append(member.getPhone1());
        sb.append("-");
        sb.append(member.getPhone2());
        sb.append("-");
        sb.append(member.getPhone3());

        phoneNumber = sb.toString();
    }
}
