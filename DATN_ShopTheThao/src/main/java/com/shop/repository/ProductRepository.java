package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query("SELECT p FROM Product p JOIN p.subcategory s JOIN s.category c WHERE c.categoryId = :categoryId")
	Page<Product> findByCategoryID(String categoryId, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.subcategory s WHERE s.subcategoryId = :subcategoryId")
	Page<Product> findBySubcategoryID(String subcategoryId, Pageable pageable);

	@Query(value="select * from Product ORDER BY ArrivalDate DESC", nativeQuery=true)
    List<Product> getProductsSortByDateDesc();
}
