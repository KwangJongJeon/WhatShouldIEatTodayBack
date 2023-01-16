package com.kj.WhatShouldIEatTodayBack.service.auth;

import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import lombok.Data;

@Data
public class MemberInfoDto {
    private String memberEmail;
    private String nickName;
    private String authorities;
}
