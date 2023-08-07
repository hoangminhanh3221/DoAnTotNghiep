package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	@Query("select o from Order o where o.orderStatus = ?1")
	 List<Order> getOrderByStatus(int status);
}
