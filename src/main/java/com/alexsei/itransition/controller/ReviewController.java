package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Image;
import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.service.ReviewService;

import com.alexsei.itransition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;


    @GetMapping("/create")
    public String getCreateReviewPage(Model model){
        model.addAttribute("review",new Review());
        return "createReviewPage";
    }

    @PostMapping("/create")
    public String createReview(Model model, @ModelAttribute Review review, Authentication authentication) throws IOException {
        reviewService.createReview(review,authentication);
        return "redirect:/profile";
    }

    @GetMapping("/show/{id}")
    public String getShowReviewPage(@PathVariable("id") Long id, Model model, Authentication authentication){
        Review review = reviewService.getReviewById(id);
        User user = userService.getUserById(review.getUserId());
        if(authentication!=null){
            User authUser = userService.getUserByAuthentication(authentication);
            model.addAttribute("authUserId",authUser.getId());
            model.addAttribute("userReviewRating",new UserReviewRating());

        }
        List<Image> images = review.getImages();

        model.addAttribute("imagesUrls",images);
        model.addAttribute("review",review);
        model.addAttribute("user",user);
        return "showReviewPage";
    }

    @GetMapping("/edit/{id}")
    public String getEditReviewPage(@PathVariable("id") Long reviewId, Model model){
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review",review);
        return "editReviewPage";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review){
        reviewService.updateReview(review);
        return "redirect:/profile";
    }

}
