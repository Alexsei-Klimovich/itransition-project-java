package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.model.User;
import com.alexsei.itransition.service.AdminService;
import com.alexsei.itransition.service.ReviewService;
import com.alexsei.itransition.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public String getAdminPage(Model model){
        List<User> users = adminService.getAllUsers();
        model.addAttribute("users",users);
        return "adminPage";
    }
    @GetMapping("/show/{id}")
    public String  getUserPage(Model model, @PathVariable("id") Long id){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        List<Review> reviews = reviewService.getReviewsByUserId(user.getId());
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
        adminService.createReview(userId,review);
        return "redirect:/admin/show/"+userId;
    }
    @GetMapping("/review/edit/{id}")
    public String getEditReviewPage(@PathVariable("id") Long id, Model model){
        Review review = reviewService.getReviewById(id);
        model.addAttribute("review",review);
        return "adminEditReviewPage";
    }

    @PostMapping("/updateReview")
    public String updateReview(@ModelAttribute Review review){
        reviewService.updateReview(review);
        return "redirect:/admin/show/"+review.getUserId();
    }


}
