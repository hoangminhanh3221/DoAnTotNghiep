package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Subcategory;

public interface SubcategoryService {
	Page<Subcategory> findAllSubcategory(Pageable pageable);
	
	List<Subcategory> findAllSubcategory();
	
	List<Subcategory> findSubcategoryByCategoryId(String categoryId);

	Optional<Subcategory> findSubcategoryById(String id);
	
	Subcategory createProduct(Subcategory Subcategory);

	Subcategory updateProduct(Subcategory Subcategory);

	void deleteSubcategory(String id);
}
