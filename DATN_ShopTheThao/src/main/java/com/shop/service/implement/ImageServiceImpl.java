package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Image;
import com.shop.repository.ImageRepository;
import com.shop.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

    private final ImageRepository ImageRepository;
	
    public ImageServiceImpl(ImageRepository ImageRepository) {
		this.ImageRepository = ImageRepository;
	}

	@Override
    public Page<Image> findAllImages(Pageable pageable) {
        return ImageRepository.findAll(pageable);
    }
    
    @Override
    public List<Image> findAllimages() {
        return ImageRepository.findAll();
    }

    @Override
    public Optional<Image> findImageById(String ImageId) {
        return ImageRepository.findById(ImageId);
    }

    @Override
    public Image createImage(Image Image) {
        return ImageRepository.save(Image);
    }

    @Override
    public Image updateImage(Image Image) {
        return ImageRepository.save(Image);
    }

    @Override
    public void deleteImage(String ImageId) {
        ImageRepository.deleteById(ImageId);
    }
}