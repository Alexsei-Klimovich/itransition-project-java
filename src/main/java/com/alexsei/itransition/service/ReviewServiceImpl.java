package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.repository.ReviewRepository;
import com.alexsei.itransition.service.interfaces.ReviewService;
import com.alexsei.itransition.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CloudinaryServiceImpl cloudinaryServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getLast20Reviews(){
        return reviewRepository.findFirst20ByOrderByIdDesc();
    }

    @Override
    public List<Review> get20MostRated(){
        return reviewRepository.findFirst20ByOrderByUserRating();
    }

    @Override
    public void createReview(Review review,Authentication authentication) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        review.setCreatingTime(formatDateTime);
        review.setHtml(MarkdownUtil.markdownToHtml(review.getText()));
        review.setUserId(userServiceImpl.getUserByAuthentication(authentication).getId());
        reviewRepository.save(review);
        cloudinaryServiceImpl.saveImages(review);
    }

    @Override
    public List<Review> getReviewsByUserId(Long userId){
        return reviewRepository.getReviewsByUserId(userId);
    }

    @Override
    public Review getReviewById(Long id){
        return reviewRepository.getById(id);
    }

    @Override
    public void updateReview(Review review){
        Review oldReview = reviewRepository.getById(review.getId());
        review.setCreatingTime(oldReview.getCreatingTime());
        review.setHtml(MarkdownUtil.markdownToHtml(review.getText()));
        review.setUserId(oldReview.getUserId());
        reviewRepository.save(review);
    }

    @Override
    public void incrementTotalUserRated(Long reviewId){
        Review reviewFromDb=reviewRepository.getById(reviewId);
        reviewFromDb.setTotalUsersRated(reviewFromDb.getTotalUsersRated()+1);
        reviewRepository.save(reviewFromDb);
    }

    @Override
    public void updateUserRating(double userRating,Long reviewId){
        Review reviewFromDb = reviewRepository.getById(reviewId);
        double averageRating= reviewFromDb.getUserRating()*reviewFromDb.getTotalUsersRated();
        averageRating=averageRating+userRating;
        Long newTotalUserRated=reviewFromDb.getTotalUsersRated()+1;
        averageRating=averageRating/Double.parseDouble(String.valueOf(newTotalUserRated));
        reviewFromDb.setUserRating(averageRating);

    }

    @Override
    public void saveReview(Review review){
        reviewRepository.save(review);
    }
}
