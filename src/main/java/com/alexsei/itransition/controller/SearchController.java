package com.alexsei.itransition.controller;

import com.alexsei.itransition.model.Review;
import com.alexsei.itransition.service.HibernateSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    HibernateSearchService hibernateSearchService;

    @GetMapping()
    public String getSearchPage(Model model, @RequestParam String search ){
        List<Review> reviews = hibernateSearchService.fuzzySearch(search);
        model.addAttribute("reviews",reviews);
        return "searchPage";
    }
}
