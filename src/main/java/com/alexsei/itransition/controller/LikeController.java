package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.service.LikeService;
import com.alexsei.itransition.service.UserService;
import org.jboss.logging.annotations.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/like")
public class LikeController {
    @Autowired
    LikeService likeService;

    @Autowired
    UserService userService;

    @PostMapping("/add/{reviewId}/{authUserId}")
    public String addLike(@PathVariable("reviewId") Long reviewId,@PathVariable("authUserId") Long authUserId){

        likeService.addLike(reviewId,authUserId);
        return "redirect:/review/show/"+reviewId;
    }
}
