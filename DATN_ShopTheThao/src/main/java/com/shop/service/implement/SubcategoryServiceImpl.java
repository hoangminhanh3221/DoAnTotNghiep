package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Subcategory;
import com.shop.repository.SubcategoryRepository;
import com.shop.service.SubcategoryService;

@Service
public class SubcategoryServiceImpl implements SubcategoryService{
	
	private final SubcategoryRepository subcategoryRepository;

	public SubcategoryServiceImpl(SubcategoryRepository subcategoryRepository) {
		this.subcategoryRepository = subcategoryRepository;
	}

	@Override
	public Page<Subcategory> findAllSubcategory(Pageable pageable) {
		return subcategoryRepository.findAll(pageable);
	}

	@Override
	public List<Subcategory> findAllSubcategory() {
		return subcategoryRepository.findAll();
	}

	@Override
	public Optional<Subcategory> findSubcategoryById(String id) {
		return subcategoryRepository.findById(id);
	}

	@Override
	public Subcategory createProduct(Subcategory Subcategory) {
		return subcategoryRepository.save(Subcategory);
	}

	@Override
	public Subcategory updateProduct(Subcategory Subcategory) {
		return subcategoryRepository.save(Subcategory);
	}

	@Override
	public void deleteSubcategory(String id) {
		subcategoryRepository.deleteById(id);
	}
}
