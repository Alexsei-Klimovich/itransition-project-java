package com.alexsei.itransition.service.interfaces;

public interface LikeService {
     void addLike(Long reviewId, Long authUserId);

     boolean isLikeExists(Long reviewId, Long authUserId);
}
