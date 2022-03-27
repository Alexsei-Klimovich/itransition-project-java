package com.alexsei.itransition.repository;

import com.alexsei.itransition.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findByUserIdAndAndReviewId(Long userId,Long ReviewId);
}
