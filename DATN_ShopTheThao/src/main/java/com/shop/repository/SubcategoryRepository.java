package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Subcategory;

public interface SubcategoryRepository  extends JpaRepository<Subcategory, String>{
	@Query("SELECT s FROM Subcategory s WHERE s.category.categoryId = :categoryId")
    List<Subcategory> findByCategoryId(String categoryId);
}
