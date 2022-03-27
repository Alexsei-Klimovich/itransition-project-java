package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @GetMapping("/")
    String getMainPage(Model model){
        List<Review> reviews = reviewServiceImpl.getLast20Reviews();
        model.addAttribute("last20Reviews",reviews);
        List<Review> mostRated = reviewServiceImpl.get20MostRated();
        model.addAttribute("mostRated",mostRated);
        return "mainPage";
    }
}
