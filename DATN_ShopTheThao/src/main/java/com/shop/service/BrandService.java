package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Brand;

public interface BrandService {
	
	Page<Brand> findAllBrand(Pageable pageable);
	
	List<Brand> findAllBrand();

	Optional<Brand> findBrandById(String id);
	
	Brand createBrand(Brand brand);

	Brand updateBrand(Brand brand);

	void deleteBrand(String id);
	
}
