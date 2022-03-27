package com.alexsei.itransition.controller;

import com.alexsei.itransition.service.LikeServiceImpl;
import com.alexsei.itransition.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeServiceImpl likeServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/add/{reviewId}/{authUserId}")
    public String addLike(@PathVariable("reviewId") Long reviewId,@PathVariable("authUserId") Long authUserId){
        likeServiceImpl.addLike(reviewId,authUserId);
        return "redirect:/review/show/"+reviewId;
    }
}
