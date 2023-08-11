package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Product;

public interface ProductService {
	
	Page<Product> findAllProduct(Pageable pageable);
	
	List<Product> findAllProduct();

	Optional<Product> findProductById(String id);
	
	Product createProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(String id);
	
	List<Product> getProductsSortByDateDesc();
	
	Page<Product> getProductsByStatusDel(Pageable pageable ,Boolean status);
}
