package com.alexsei.itransition.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="likes")
public class Like {
    @Id
    @Column(name = "like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LikeId;

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id")
    private Long userId;

}
