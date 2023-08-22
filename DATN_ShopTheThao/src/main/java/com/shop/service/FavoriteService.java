package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Favorite;

public interface FavoriteService{
	List<Favorite> findAllFavorites();
	
	Page<Favorite> findAllFavorites(Pageable pageable);
	
	Optional<Favorite> findFavoriteById(Integer favoriteId);
	
	Favorite createFavorite(Favorite favorite);
	
	Favorite updateFavorite(Favorite favorite);
	
	void deleteFavorite(Integer favoriterId);
	
	List<Favorite> findByUsername(String userName);
}