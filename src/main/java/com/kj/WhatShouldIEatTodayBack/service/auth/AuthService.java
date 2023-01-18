package com.kj.WhatShouldIEatTodayBack.service.auth;

import com.kj.WhatShouldIEatTodayBack.controller.dto.EditUserFormDto;
import com.kj.WhatShouldIEatTodayBack.controller.dto.RegisterFormDto;
import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityManagerFactory entityManagerFactory;

    /**
     * @param authentication 로그인한 유저
     * @return 만약 오류 없는 authentication 일 경우 MemberInfoDto 객체 반환, 없을경우 null 반환
     */
    public MemberInfoDto getMemberInfo(Authentication authentication) {

        Optional<Member> memberOpt = memberRepository.findByMemberEmail((String) authentication.getPrincipal());

        if(memberOpt.isPresent()) {
            Member member = memberOpt.get();
            MemberInfoDto memberInfoDto = new MemberInfoDto();
            memberInfoDto.setMemberEmail(member.getMemberEmail());
            memberInfoDto.setNickName(member.getNickName());
            memberInfoDto.setAuthorities(member.getMemberRole().toString());

            log.info("email: {}", member.getMemberEmail());
            log.info("nickname: {}", member.getNickName());
            log.info("role: {}", member.getMemberRole().toString());

            return memberInfoDto;
        }
        else
            return null;
    }

    /**
     * @param authentication 로그인한 유저
     * @return 만약 오류 없는 authentication 일 경우 MemberInfoDetailDto 객체 반환, 없을경우 null 반환
     */
    public MemberInfoDetailDto getMemberInfoDetail(Authentication authentication) {

        Optional<Member> memberOpt = memberRepository.findByMemberEmail((String) authentication.getPrincipal());

        if(memberOpt.isPresent()) {
            Member member = memberOpt.get();
            MemberInfoDetailDto memberInfoDetailDto = new MemberInfoDetailDto();
            memberInfoDetailDto.setMemberEmail(member.getMemberEmail());
            memberInfoDetailDto.setNickName(member.getNickName());
            memberInfoDetailDto.setName(member.getName());
            memberInfoDetailDto.setPhoneNumberFormatted(member);
            memberInfoDetailDto.setAuthorities(member.getMemberRole().toString());

            log.info("email: {}", memberInfoDetailDto.getMemberEmail());
            log.info("nickname: {}", memberInfoDetailDto.getNickName());

            return memberInfoDetailDto;
        }
        else
            return null;
    }


    /**
     * @return null 로그인 실패
     */
    public Member login(String memberEmail, String memberPassword) {
        return memberRepository.findByMemberEmail(memberEmail)
                .filter(m -> passwordEncoder.matches(memberPassword, m.getMemberPw()))
                .orElse(null);
    }

    public void register(RegisterFormDto registerFormDto) {
        Member member = registerFormDto.makeEntityExceptPassword();
        member.encodePassword(passwordEncoder.encode(registerFormDto.getMemberPw()));
        memberRepository.save(member);
    }

    public boolean checkMemberIsUnique(RegisterFormDto registerFormDto, BindingResult result) {
        Member member = registerFormDto.makeEntityExceptPassword();

        if(!memberRepository.findByMemberEmail(member.getMemberEmail()).isEmpty()) {
            return false;
        }

        return true;
    }

    public boolean checkMemberNicknameIsUnique(String nickName, BindingResult result) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Member> resultList = em.createQuery("select m From Member m where m.nickName = :nickName", Member.class)
                .setParameter("nickName", nickName)
                .getResultList();

        if(resultList.isEmpty()) return true;

        for (Member member : resultList) {
            log.info("memberEmail = {}", member);
        }
        return false;
    }

    public boolean checkMemberNicknameIsUnique(String email, String nickName, BindingResult result) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Member> resultList = em.createQuery("select m From Member m where m.nickName = :nickName", Member.class)
                .setParameter("nickName", nickName)
                .getResultList();

        if(resultList.isEmpty()) return true;


        for (Member member : resultList) {
            log.info("memberEmail = {}", member);
        }
        return false;
    }

    /**
     *
     * @param authentication 유저의 auth
     * @param nickname 변경할 닉네임
     * @return 변경된 닉네임
     */
    @Transactional
    public String changeNickname(Authentication authentication, String nickname) {
        String email = authentication.getPrincipal().toString();
        Member member = memberRepository.findByMemberEmail(email).get();
        member.changeNickName(nickname);

        return member.getNickName();
    }

    @Transactional
    public String changePhoneNumber(Authentication authentication, String phoneNumber) {
        String email = authentication.getPrincipal().toString();
        Member member = memberRepository.findByMemberEmail(email).get();

        String ret = member.changePhoneNumber(phoneNumber);

        return ret;
    }

    @Transactional
    public Member updateUser(EditUserFormDto editUserFormDto) {

        Member member = memberRepository.findByMemberEmail(editUserFormDto.getMemberEmail()).get();
        member.changeNickName(editUserFormDto.getNickName());
        member.changePhoneNumber(editUserFormDto.getPhoneNumber());

        return member;
    }

    @Transactional
    public boolean changePassword(Authentication authentication, String password) {
        String email = authentication.getPrincipal().toString();
        Member member = memberRepository.findByMemberEmail(email).get();
        member.encodePassword(passwordEncoder.encode(password));
        return true;
    }
}
