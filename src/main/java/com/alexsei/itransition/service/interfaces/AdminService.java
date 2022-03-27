package com.alexsei.itransition.service.interfaces;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    void createReview(Long userId, Review review) throws IOException;

    List<User> getAllUsers();
}
