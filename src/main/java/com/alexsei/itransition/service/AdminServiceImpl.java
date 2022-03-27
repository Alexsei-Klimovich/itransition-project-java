package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.service.interfaces.AdminService;
import com.alexsei.itransition.util.MarkdownUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ReviewServiceImpl reviewServiceImpl;

    @Autowired
    CloudinaryServiceImpl cloudinaryServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public List<User> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }

    @Override
    public void createReview(Long userId, Review review) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDateTime = now.format(formatter);
        review.setCreatingTime(formatDateTime);
        review.setHtml(MarkdownUtil.markdownToHtml(review.getText()));
        review.setUserId(userId);
        reviewServiceImpl.saveReview(review);
        cloudinaryServiceImpl.saveImages(review);
    }
}
