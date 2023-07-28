package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Discount;

public interface DiscountRepository  extends JpaRepository<Discount, String>{

}