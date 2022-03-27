package com.alexsei.itransition.service;

import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.repository.UserReviewRatingRepository;
import com.alexsei.itransition.service.interfaces.UserReviewRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReviewRatingServiceImpl implements UserReviewRatingService {

    @Autowired
    UserReviewRatingRepository userReviewRatingRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ReviewServiceImpl reviewServiceImpl;

    @Override
    public void addRating(Long reviewId, Long authUserId, double userRating){
        UserReviewRating userReviewRating = new UserReviewRating();
        userReviewRating.setReviewId(reviewId);
        userReviewRating.setUserId(authUserId);
        userReviewRating.setUserRating(userRating);
        if(!isUserRatingExists(reviewId,authUserId)){
            reviewServiceImpl.updateUserRating(userRating,reviewId);
            reviewServiceImpl.incrementTotalUserRated(reviewId);
            userReviewRatingRepository.save(userReviewRating);
        }
    }

    @Override
    public boolean isUserRatingExists(Long reviewId, Long authUserId){
        if(userReviewRatingRepository.findByUserIdAndAndReviewId(authUserId,reviewId)!=null){
            return true;
        } else{
            return false;
        }
    }
}
