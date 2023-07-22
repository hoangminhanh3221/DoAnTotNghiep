package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{
}
