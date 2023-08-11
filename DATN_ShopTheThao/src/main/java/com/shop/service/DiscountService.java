package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Discount;


public interface DiscountService {
	Page<Discount> findAllDiscount(Pageable pageable);
	
	List<Discount> findAllDiscount();

	Optional<Discount> findDiscountById(String id);
	
	Discount createDiscount(Discount Discount);

	Discount updateDiscount(Discount Discount);

	void deleteDiscount(String id);
}
