package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query(value ="select * from orders  where OrderStatus = ?1", nativeQuery=true)
	 List<Order> getOrderByStatus(String status);
	
}
