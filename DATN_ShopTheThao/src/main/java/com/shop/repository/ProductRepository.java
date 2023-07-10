package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	//List<Product> findAllByCategoryId(String categoryId);
	
	@Query("SELECT p FROM Product p " +
		       "JOIN p.subcategory s " +
		       "JOIN s.category c " +
		       "WHERE c.categoryId = ?1")
	Page<Product> findAllByCategoryId(String categoryId, Pageable pageable);
	
	@Query("SELECT p FROM Product p " +
		       "WHERE p.subcategory.subcategoryId = ?1")
	Page<Product> findAllBySubcategoryId(String subcategoryId, Pageable pageable);

}
