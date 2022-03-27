package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.service.UserReviewRatingServiceImpl;
import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class UserReviewRatingController {

    @Autowired
    UserReviewRatingServiceImpl userReviewRatingServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/add/{reviewId}/{authUserId}")
    public String addUserRating(@PathVariable("reviewId") Long reviewId, @ModelAttribute UserReviewRating userReviewRating, @PathVariable("authUserId") Long authUserId){
        userReviewRatingServiceImpl.addRating(reviewId,authUserId,userReviewRating.getUserRating());
        return "redirect:/review/show/"+reviewId;
    }
}



