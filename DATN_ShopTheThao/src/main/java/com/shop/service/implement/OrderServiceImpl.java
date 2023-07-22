package com.shop.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.OrderRepository;
import com.shop.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;

	public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
		this.orderRepository = orderRepository;
		this.orderDetailRepository = orderDetailRepository;
	}

	@Override
	public Page<Order> findAllOrders(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findOrderById(Integer orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer orderId) {
		orderRepository.deleteById(orderId);
	}

	@Override
	public Order createJson(JsonNode orderData) {
		

		return null;
	}
}