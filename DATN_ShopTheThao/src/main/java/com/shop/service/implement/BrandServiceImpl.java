package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Brand;
import com.shop.repository.BrandRepository;
import com.shop.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
	
	private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

	@Override
	public Page<Brand> findAllBrand(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	@Override
	public List<Brand> findAllBrand() {
		return brandRepository.findAll();
	}

	@Override
	public Optional<Brand> findBrandById(String id) {
		return brandRepository.findById(id);
	}

	@Override
	public Brand createBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(String id) {
		brandRepository.deleteById(id);
		
	}
    
}