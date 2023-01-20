package com.kj.WhatShouldIEatTodayBack.service.auth;

import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import lombok.Data;

/**
 * 유저의 간략한 정보를 포함합니다.(이메일, 닉네임, 권한)
 */
@Data
public class MemberInfoDto {

    private String memberEmail;
    private String nickName;
    private String authorities;
    private String photosImagePath;
}
