package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Like;
import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.repository.LikeRepository;
import com.alexsei.itransition.service.interfaces.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ReviewServiceImpl reviewServiceImpl;

    @Override
    public void addLike(Long reviewId, Long authUserId){
        Like like = new Like();
        like.setReviewId(reviewId);
        like.setUserId(authUserId);
        if(!isLikeExists(reviewId,authUserId)){
            Review review =  reviewServiceImpl.getReviewById(reviewId);
            userServiceImpl.incrementTotalLikes(review.getUserId());
            likeRepository.save(like);
        }
    }

    @Override
    public boolean isLikeExists(Long reviewId, Long authUserId){
        if(likeRepository.findByUserIdAndAndReviewId(authUserId,reviewId)!=null){
            return true;
        } else{
            return false;
        }
    }
}
