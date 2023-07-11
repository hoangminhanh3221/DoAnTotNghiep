package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Order;

public interface OrderService{
	List<Order> findAllOrders();
	
	Page<Order> findAllOrders(Pageable pageable);
	
	Optional<Order> findOrderById(Integer orderId);
	
	Order createOrder(Order order);
	
	Order updateOrder(Order order);
	
	void deleteOrder(Integer orderId);
}