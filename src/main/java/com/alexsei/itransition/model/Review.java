package com.alexsei.itransition.model;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Indexed
@Entity
public class Review {
    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    @Field(name = "name")
    @Column(name = "name")
    private String name;
    @Field(name = "group")
    @Column(name = "review_group")
    private String group;
    @Field(name = "text")
    @Column(name = "text")
    private String text;

    @Column(name = "html")
    private String html;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name="review_images",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> images = new ArrayList<>();

    @Transient
    private MultipartFile[] files;

    @Column(name = "creator_rating")
    private double creatorRating;

    @Column(name = "user_rating")
    private double userRating=0;

    @Column(name="creating_time")
    private LocalDateTime creatingTime;

    public void addImage(Image image){
        images.add(image);
    }

}
