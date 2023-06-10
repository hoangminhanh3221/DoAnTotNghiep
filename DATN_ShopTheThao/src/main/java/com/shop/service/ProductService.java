package com.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Product;

public interface ProductService {
	
	Page<Product> findAll(Pageable pageable);
	
	Page<Product> findByCategoryID(String cid,Pageable pageable);
	
	List<Product> findAll();

	Product findById(String productID);
	
	Product create(Product product);

	Product update(Product product);

	void delete(String id);
	
}
