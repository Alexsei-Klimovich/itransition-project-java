package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Like;
import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    public void addLike(Long reviewId, Long authUserId){
        Like like = new Like();
        like.setReviewId(reviewId);
        like.setUserId(authUserId);
        if(!isLikeExists(reviewId,authUserId)){
            Review review =  reviewService.getReviewById(reviewId);
            userService.incrementTotalLikes(review.getUserId());
            likeRepository.save(like);
        }
    }

    public boolean isLikeExists(Long reviewId, Long authUserId){
        if(likeRepository.findByUserIdAndAndReviewId(authUserId,reviewId)!=null){
            return true;
        } else{
            return false;
        }
    }



}
