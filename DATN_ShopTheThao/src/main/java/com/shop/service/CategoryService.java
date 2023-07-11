package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Category;

public interface CategoryService {
	
	Page<Category> findAllCategory(Pageable pageable);
	
	List<Category> findAllCategory();

	Optional<Category> findCategoryById(String id);
	
	Category createCategory(Category category);

	Category updateCategory(Category category);

	void deleteCategory(String id);
	
}
