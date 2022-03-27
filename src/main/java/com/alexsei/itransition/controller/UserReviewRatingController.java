package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.service.UserReviewRatingService;
import com.alexsei.itransition.service.UserService;
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
    UserReviewRatingService userReviewRatingService;

    @Autowired
    UserService userService;

    @PostMapping("/add/{reviewId}/{authUserId}")
    public String addUserRating(@PathVariable("reviewId") Long reviewId, @ModelAttribute UserReviewRating userReviewRating, @PathVariable("authUserId") Long authUserId){
        userReviewRatingService.addRating(reviewId,authUserId,userReviewRating.getUserRating());
        return "redirect:/review/show/"+reviewId;
    }
}



