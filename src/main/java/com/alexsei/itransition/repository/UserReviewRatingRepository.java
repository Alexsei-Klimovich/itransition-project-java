package com.alexsei.itransition.repository;

import com.alexsei.itransition.model.Like;
import com.alexsei.itransition.model.UserReviewRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRatingRepository extends JpaRepository<UserReviewRating,Long> {
    UserReviewRating findByUserIdAndAndReviewId(Long userId, Long ReviewId);
}
