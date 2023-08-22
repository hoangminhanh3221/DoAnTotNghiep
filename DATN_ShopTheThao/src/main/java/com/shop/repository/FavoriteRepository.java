package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Favorite;
import com.shop.entity.Product;

public interface FavoriteRepository  extends JpaRepository<Favorite, Integer>{
	@Query("Select f from Favorite f where f.account.username = :userName")
	List<Favorite> findByUsername(String userName);
}
