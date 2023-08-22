package com.shop.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.JsonNode;
import com.shop.entity.Order;

public interface OrderService{
	List<Order> findAllOrders();
	
	Page<Order> findAllOrders(Pageable pageable);
	
	Optional<Order> findOrderById(Integer orderId);
	
	Order createOrder(Order order);
	
	Order updateOrder(Order order);
	
	void deleteOrder(Integer orderId);

	List<Order> getOrdersByYear(int year);

	List<Integer> getYear();

	List<Order> getOrdersByMonth(int monthInput, int year );

	List<Order> getRevenueFromTo(Date from, Date to);
	
}