package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Image;

public interface ImageService{
	List<Image> findAllimages();
	
	Page<Image> findAllImages(Pageable pageable);
	
	Optional<Image> findImageById(Integer imageId);
	
	Image createImage(Image image);
	
	Image updateImage(Image image);
	
	void deleteImage(Integer imageId);
}