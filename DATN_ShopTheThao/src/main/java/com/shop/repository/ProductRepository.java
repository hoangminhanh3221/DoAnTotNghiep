package com.shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shop.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	@Query("SELECT p FROM Product p JOIN p.subcategory s JOIN s.category c WHERE c.categoryId = :categoryId")
	Page<Product> findByCategoryID(String categoryId, Pageable pageable);
	
	@Query("SELECT p FROM Product p JOIN p.subcategory s WHERE s.subcategoryId = :subcategoryId")
	Page<Product> findBySubcategoryID(String subcategoryId, Pageable pageable);

	@Query(value="select * from Product ORDER BY ArrivalDate DESC", nativeQuery=true)
    List<Product> getProductsSortByDateDesc();
	
	@Query("SELECT od.product.productId FROM OrderDetail od GROUP BY od.product ORDER BY COUNT(od.product) DESC")
	List<String> findBestSellingProducts();

	@Query("SELECT f.product.productId FROM Favorite f GROUP BY f.product ORDER BY COUNT(f.product) DESC")
	List<String> findMostLikedProducts();
	
	@Query("SELECT p FROM Product p JOIN p.discount d WHERE d.discountRate > 0")
	List<Product> findProductsOnSale();
	
	@Query("SELECT p FROM Product p WHERE p.isDeleted = :status")
    Page<Product> findDeletedProducts(Pageable pageable,@Param("status") Boolean status);

}
