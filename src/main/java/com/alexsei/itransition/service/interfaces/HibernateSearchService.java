package com.alexsei.itransition.service.interfaces;


import com.alexsei.itransition.model.Review;

import java.util.List;

public interface HibernateSearchService {
     List<Review> fuzzySearch(String searchTerm);
}
