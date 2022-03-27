package com.alexsei.itransition.service.interfaces;

import com.alexsei.itransition.model.Review;

import java.io.IOException;

public interface CloudinaryService {
     void saveImages(Review review) throws IOException;
}
