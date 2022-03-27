package com.alexsei.itransition.service.interfaces;

import com.alexsei.itransition.model.Review;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    List<Review> getLast20Reviews();

    List<Review> get20MostRated();

    void createReview(Review review, Authentication authentication) throws IOException;

    List<Review> getReviewsByUserId(Long userId);

    Review getReviewById(Long id);

    void updateReview(Review review);

    void incrementTotalUserRated(Long reviewId);

    void updateUserRating(double userRating,Long reviewId);

    void saveReview(Review review);
}
