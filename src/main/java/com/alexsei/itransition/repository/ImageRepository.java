package com.alexsei.itransition.repository;

import com.alexsei.itransition.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
@Transactional
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
