package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.service.ReviewServiceImpl;
import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    String getProfilePage( Model model, Authentication authentication){
        User user = userServiceImpl.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        List<Review> reviews = reviewServiceImpl.getReviewsByUserId(user.getId());
        model.addAttribute("usersReviews",reviews);
        return "profilePage";
    }
}
