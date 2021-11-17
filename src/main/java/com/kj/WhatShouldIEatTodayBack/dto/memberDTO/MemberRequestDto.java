package com.kj.WhatShouldIEatTodayBack.dto.memberDTO;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import com.kj.WhatShouldIEatTodayBack.enums.MemberRole;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberRequestDto {

    private Long id;

    @Email(message = "메일의 양식이 잘못되었습니다.")
    @NotBlank(message = "메일을 작성해주세요")
    private String memberEmail;

    @NotBlank(message = "패스워드를 작성해주세요")
    private String memberPw;

    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String nickName;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리 숫자만 입력 가능합니다.")
    private String phoneNumber;

    private String[] parsePhone() {
        String[] phones = new String[3];
        int mid = phoneNumber.length() == 10? 7: 8;

        phones[0] = phoneNumber.substring(0, 3);
        phones[1] = phoneNumber.substring(3, mid);
        phones[2] = phoneNumber.substring(mid);

        return phones;
    }

    private Member toEntity() {
        String[] phones = parsePhone();
        return Member.builder()
                .memberEmail(memberEmail)
                .memberPw(memberPw)
                .name(name)
                .phone1(phones[0])
                .phone2(phones[1])
                .phone3(phones[2])
                .memberRole(MemberRole.USER)
                .build();
    }


}
