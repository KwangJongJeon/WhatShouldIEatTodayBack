package com.kj.WhatShouldIEatTodayBack.dto.memberDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@Data
public class MemberEditRequestDto {

    private Long memberId;

    @Email(message = "메일의 양식이 잘못되었습니다.")
    private String memberEmail;

    @NotBlank(message = "이름을 입력해주세요")
    private String memberName;

    private String nickName;

    @NotBlank(message = "핸드폰 번호를 입력해주세요")
    private String phoneNumber;
}
