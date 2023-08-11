package com.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	Page<Order> findAllByOrderStatus(String orderStatus, Pageable pageable);
}
