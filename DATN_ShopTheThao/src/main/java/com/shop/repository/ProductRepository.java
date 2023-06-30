package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	List<Product> findBySubcategory_Category_CategoryId(String categoryId);
}
