package com.kj.WhatShouldIEatTodayBack.domain.review.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository{

    public Review save(Review review);
    public Review update(Review review);
    public Review updateContentById(Long id, String content);
    public Optional<Review> findById(Long id);
    public List<Review> findAll();
    public List<Review> findReviewByMember(Member member);
    public List<Review> findReviewByStore(Store store);
    public List<Review> findReviewByStore(Long storeId);
}
