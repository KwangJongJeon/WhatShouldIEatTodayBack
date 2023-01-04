package com.kj.WhatShouldIEatTodayBack.domain.review.repository;

import com.kj.WhatShouldIEatTodayBack.domain.member.Member;
import com.kj.WhatShouldIEatTodayBack.domain.review.Review;
import com.kj.WhatShouldIEatTodayBack.domain.store.Store;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    public Review save(Review review);
    public Optional<Review> findById(Long id);
    public List<Review> findReviewByMember(Member member);
    public List<Review> findReviewByStore(Store store);
}
