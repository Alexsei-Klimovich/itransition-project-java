package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.repository.ReviewRepository;
import com.alexsei.itransition.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public List<Review> getLast20Reviews(){
        return reviewRepository.findFirst20ByOrderByIdDesc();
    }
    public List<Review> get20MostRated(){
        return reviewRepository.findFirst20ByOrderByUserRating();
    }

    public void createReview(Review review,Authentication authentication) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        review.setCreatingTime(formatDateTime);
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

    public void incrementTotalUserRated(Long reviewId){
        Review reviewFromDb=reviewRepository.getById(reviewId);
        reviewFromDb.setTotalUsersRated(reviewFromDb.getTotalUsersRated()+1);
        reviewRepository.save(reviewFromDb);
    }

    public void updateUserRating(double userRating,Long reviewId){
        Review reviewFromDb = reviewRepository.getById(reviewId);
        double averageRating= reviewFromDb.getUserRating()*reviewFromDb.getTotalUsersRated();
        averageRating=averageRating+userRating;
        Long newTotalUserRated=reviewFromDb.getTotalUsersRated()+1;
        averageRating=averageRating/Double.parseDouble(String.valueOf(newTotalUserRated));
        reviewFromDb.setUserRating(averageRating);

    }

    public void saveReview(Review review){
        reviewRepository.save(review);
    }

}
