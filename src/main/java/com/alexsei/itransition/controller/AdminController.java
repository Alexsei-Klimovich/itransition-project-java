package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.service.AdminServiceImpl;
import com.alexsei.itransition.service.ReviewServiceImpl;
import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    ReviewServiceImpl reviewServiceImpl;

    @GetMapping
    public String getAdminPage(Model model){
        List<User> users = adminServiceImpl.getAllUsers();
        model.addAttribute("users",users);
        return "adminPage";
    }
    @GetMapping("/show/{id}")
    public String  getUserPage(Model model, @PathVariable("id") Long id){
        User user = userServiceImpl.getUserById(id);
        model.addAttribute("user", user);
        List<Review> reviews = reviewServiceImpl.getReviewsByUserId(user.getId());
        model.addAttribute("usersReviews",reviews);
        return "adminProfilePage";
    }

    @GetMapping("/review/create/{id}")
    public String  getCreateReviewPage(Model model, @PathVariable("id") Long userid){
        model.addAttribute("userId",userid);
        model.addAttribute("review",new Review());
        return "adminCreateReviewPage";
    }

    @PostMapping("/review/create/{id}")
    public String createReview(Model model, @PathVariable("id") Long userId ,@ModelAttribute Review review) throws IOException {
        adminServiceImpl.createReview(userId,review);
        return "redirect:/admin/show/"+userId;
    }

    @GetMapping("/review/edit/{id}")
    public String getEditReviewPage(@PathVariable("id") Long id, Model model){
        Review review = reviewServiceImpl.getReviewById(id);
        model.addAttribute("review",review);
        return "adminEditReviewPage";
    }

    @PostMapping("/updateReview")
    public String updateReview(@ModelAttribute Review review){
        reviewServiceImpl.updateReview(review);
        return "redirect:/admin/show/"+review.getUserId();
    }
}
