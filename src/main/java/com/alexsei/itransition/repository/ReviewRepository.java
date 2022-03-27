package com.alexsei.itransition.repository;

import com.alexsei.itransition.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findFirst20ByOrderByIdDesc();
    List<Review> getReviewsByUserId(Long userId);
    List<Review> findFirst20ByOrderByUserRating();


}
