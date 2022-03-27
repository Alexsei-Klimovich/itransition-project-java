package com.alexsei.itransition.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name="user_review_rating")
public class UserReviewRating {

    @Id
    @Column(name = "rating_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_rating")
    private double userRating;

    @Column(name = "user_id")
    private Long userId;
}
