package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Category;
import com.shop.repository.CategoryRepository;
import com.shop.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryRepository CategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.CategoryRepository = categoryRepository;
    }

	@Override
	public Page<Category> findAllCategory(Pageable pageable) {
		return CategoryRepository.findAll(pageable);
	}

	@Override
	public List<Category> findAllCategory() {
		return CategoryRepository.findAll();
	}

	@Override
	public Optional<Category> findCategoryById(String id) {
		return CategoryRepository.findById(id);
	}

	@Override
	public Category createCategory(Category category) {
		return CategoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		return CategoryRepository.save(category);
	}

	@Override
	public void deleteCategory(String id) {
		CategoryRepository.deleteById(id);
		
	}
    
}
