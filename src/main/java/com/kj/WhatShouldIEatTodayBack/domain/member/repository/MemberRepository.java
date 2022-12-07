package com.kj.WhatShouldIEatTodayBack.domain.member.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberEmailAndMemberPw(String memberEmail, String memberPw);
    Optional<Member> findByMemberEmail(String memberEmail);
}
