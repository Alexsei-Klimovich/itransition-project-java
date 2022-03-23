package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.repository.ReviewRepository;
import com.alexsei.itransition.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getLast5Reviews(){
        return reviewRepository.findFirst5ByOrderByIdDesc();
    }

    public void createReview(Review review,Authentication authentication) throws IOException {
        review.setCreatingTime(LocalDateTime.now());
        review.setHtml(MarkdownUtil.markdownToHtml(review.getText()));
        review.setUserId(userService.getUserByAuthentication(authentication).getId());
        reviewRepository.save(review);
        cloudinaryService.saveImages(review);
    }

    public List<Review> getReviewsByUserId(Long userId){
        return reviewRepository.getReviewsByUserId(userId);
    }

    public Review getReviewById(Long id){
        return reviewRepository.getById(id);
    }

    public void updateReview(Review review){
        Review oldReview = reviewRepository.getById(review.getId());
        review.setCreatingTime(oldReview.getCreatingTime());
        review.setHtml(MarkdownUtil.markdownToHtml(review.getText()));
        review.setUserId(oldReview.getUserId());
        reviewRepository.save(review);
    }

}
