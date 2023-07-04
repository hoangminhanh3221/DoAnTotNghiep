package com.shop.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
	
}
