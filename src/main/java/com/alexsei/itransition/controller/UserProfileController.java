package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.security.oauth.CustomOAuth2User;
import com.alexsei.itransition.service.ReviewService;
import com.alexsei.itransition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @GetMapping
    String getProfilePage( Model model, Authentication authentication){
        User user = userService.getUserByAuthentication(authentication);
        model.addAttribute("user", user);
        List<Review> reviews = reviewService.getReviewsByUserId(user.getId());
        model.addAttribute("usersReviews",reviews);
        return "profilePage";
    }


}
