package com.alexsei.itransition.service.interfaces;

public interface UserReviewRatingService {
    void addRating(Long reviewId, Long authUserId, double userRating);

    boolean isUserRatingExists(Long reviewId, Long authUserId);
}
