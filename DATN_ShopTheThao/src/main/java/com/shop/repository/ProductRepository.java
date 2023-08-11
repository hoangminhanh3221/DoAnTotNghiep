package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query(value="select * from Product ORDER BY ArrivalDate DESC", nativeQuery=true)
    List<Product> getProductsSortByDateDesc();
	
	@Query("SELECT p FROM Product p WHERE p.isDeleted = :status")
    Page<Product> findDeletedProducts(Pageable pageable,@Param("status") Boolean status);
}
