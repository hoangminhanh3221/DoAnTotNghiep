package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query(value="select * from Product ORDER BY ArrivalDate DESC", nativeQuery=true)
    List<Product> getProductsSortByDateDesc();

}
