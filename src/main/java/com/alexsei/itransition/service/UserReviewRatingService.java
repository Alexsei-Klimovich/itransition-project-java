package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Like;
import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.repository.UserReviewRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReviewRatingService {

    @Autowired
    UserReviewRatingRepository userReviewRatingRepository;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    public void addRating(Long reviewId, Long authUserId, double userRating){
        UserReviewRating userReviewRating = new UserReviewRating();
        userReviewRating.setReviewId(reviewId);
        userReviewRating.setUserId(authUserId);
        userReviewRating.setUserRating(userRating);

        if(!isUserRatingExists(reviewId,authUserId)){

            reviewService.updateUserRating(userRating,reviewId);
            reviewService.incrementTotalUserRated(reviewId);
            userReviewRatingRepository.save(userReviewRating);
        }
    }

    public boolean isUserRatingExists(Long reviewId, Long authUserId){
        if(userReviewRatingRepository.findByUserIdAndAndReviewId(authUserId,reviewId)!=null){
            return true;
        } else{
            return false;
        }
    }



}
