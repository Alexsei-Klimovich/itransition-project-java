package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Image;
import com.alexsei.itransition.model.Review;
import com.cloudinary.*;
import com.cloudinary.transformation.Layer;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dz4zb1lua",
            "api_key", "692833689656671",
            "api_secret", "cx7ZH6BrgB0oUfUA6o4WjeYi2tk"));

    public void saveImages(Review review) throws IOException {
        for(MultipartFile file: review.getFiles()) {
            File newFile = File.createTempFile("App-","-.png");
            file.transferTo(newFile);
            Map uploadResult = cloudinary.uploader().upload(newFile, ObjectUtils.emptyMap());
            Image image = new Image();
            image.setLink(uploadResult.get("url").toString());
            review.addImage(image);
            newFile.deleteOnExit();
        }
    }

}
