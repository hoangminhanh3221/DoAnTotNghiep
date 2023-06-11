package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Brand;
import com.shop.repository.BrandRepository;
import com.shop.service.BrandService;

public class BrandServiceImpl implements BrandService{
	
	private final BrandRepository BrandRepository;

    public BrandServiceImpl(BrandRepository BrandRepository) {
        this.BrandRepository = BrandRepository;
    }

	@Override
	public Page<Brand> findAllBrand(Pageable pageable) {
		return BrandRepository.findAll(pageable);
	}

	@Override
	public List<Brand> findAllBrand() {
		return BrandRepository.findAll();
	}

	@Override
	public Optional<Brand> findBrandById(String id) {
		return BrandRepository.findById(id);
	}

	@Override
	public Brand createBrand(Brand brand) {
		return BrandRepository.save(brand);
	}

	@Override
	public Brand updateBrand(Brand brand) {
		return BrandRepository.save(brand);
	}

	@Override
	public void deleteBrand(String id) {
		BrandRepository.deleteById(id);
		
	}
    
}
