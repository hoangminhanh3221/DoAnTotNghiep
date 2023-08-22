package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Favorite;
import com.shop.entity.Product;

public interface FavoriteRepository  extends JpaRepository<Favorite, Integer>{

}
