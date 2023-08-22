package com.shop.service.implement;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.shop.entity.Order;
import com.shop.repository.OrderRepository;
import com.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
	
    public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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
    public List<Order> getOrdersByYear(int year){
        return orderRepository.getOrderByYear(year);
    }
    
    @Override
    public List<Integer> getYear(){
        return orderRepository.getYear();
    }
    
    @Override
    public List<Order> getOrdersByMonth(int monthInput, int year){
        return orderRepository.getOrderByMonth(monthInput, year);
    }
    
	@Override
	public List<Order> getRevenueFromTo(Date from, Date to) {
		return orderRepository.findOrdersInDateRange(from, to);
	}
	
	@Override
	public List<Order> findOrderByUserName(String userName) {
		// TODO Auto-generated method stub
		return orderRepository.findOrdersByUserName(userName);
	}
}