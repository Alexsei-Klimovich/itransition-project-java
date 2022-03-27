package com.alexsei.itransition.controller;

import com.alexsei.itransition.exception.NoAccessException;
import com.alexsei.itransition.model.Image;
import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.model.UserReviewRating;
import com.alexsei.itransition.service.ReviewServiceImpl;

import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping("/create")
    public String getCreateReviewPage(Model model){
        model.addAttribute("review",new Review());
        return "createReviewPage";
    }

    @PostMapping("/create")
    public String createReview(Model model, @ModelAttribute Review review, Authentication authentication) throws IOException {
        reviewServiceImpl.createReview(review,authentication);
        return "redirect:/profile";
    }

    @GetMapping("/show/{id}")
    public String getShowReviewPage(@PathVariable("id") Long id, Model model, Authentication authentication){
        Review review = reviewServiceImpl.getReviewById(id);
        User user = userServiceImpl.getUserById(review.getUserId());
        if(authentication!=null){
            User authUser = userServiceImpl.getUserByAuthentication(authentication);
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
    public String getEditReviewPage(@PathVariable("id") Long reviewId, Model model,Authentication authentication){
       if(reviewServiceImpl.isRealUser(reviewId,authentication)){
           Review review = reviewServiceImpl.getReviewById(reviewId);
           model.addAttribute("review",review);
           return "editReviewPage";
       } else {
           throw new NoAccessException();
       }
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long reviewId, Authentication authentication){
        reviewServiceImpl.deleteReviewById(reviewId, authentication);
        return "redirect:/profile";
    }

    @PostMapping("/update")
    public String updateReview(@ModelAttribute Review review){
        reviewServiceImpl.updateReview(review);
        return "redirect:/profile";
    }

}
