package com.kj.WhatShouldIEatTodayBack.controller.dto;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import com.kj.WhatShouldIEatTodayBack.validator.Password;
import lombok.Data;
import org.checkerframework.common.aliasing.qual.Unique;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterFormDto {

    @NotBlank
    @Unique
    private String memberEmail;

    @Password
    private String memberPw;

    @NotBlank
    private String memberName;

    @NotBlank
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리 숫자만 입력 가능합니다.")
    private String phoneNumber;


    private String[] parsePhone() {
        String[] phones = new String[3];
        int mid = phoneNumber.length() == 10? 6: 7;

        phones[0] = phoneNumber.substring(0, 3);
        phones[1] = phoneNumber.substring(3, mid);
        phones[2] = phoneNumber.substring(mid);

        return phones;
    }

    public Member makeEntityExceptPassword() {
        String[] phones = parsePhone();
        return Member.builder()
                .memberEmail(memberEmail)
                .name(memberName)
                .phone1(phones[0])
                .phone2(phones[1])
                .phone3(phones[2])
                .memberRole(MemberRole.USER)
                .build();
    }
}
