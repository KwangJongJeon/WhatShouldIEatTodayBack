package com.kj.WhatShouldIEatTodayBack.domain.repository;

import com.kj.WhatShouldIEatTodayBack.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberEmailAndMemberPw(String memberEmail, String memberPw);
    Optional<Member> findByMemberEmail(String memberEmail);
}
