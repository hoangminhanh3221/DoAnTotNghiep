package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.shop.entity.Order;
import com.shop.repository.OrderInfoRepository;
import com.shop.repository.OrderRepository;

@Service
public interface OrderService{
	
	List<Order> findAllOrders();
	
	Page<Order> findAllOrders(Pageable pageable);
	
	Optional<Order> findOrderById(Integer orderId);
	
	Order createJson(JsonNode orderData);
	
	Order createOrder(Order order);
	
	Order updateOrder(Order order);
	
	void deleteOrder(Integer orderId);
	
	
}