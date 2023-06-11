package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.OrderDetail;

public interface OrderdetailService{
	List<OrderDetail> findAllOrderDetails();
	
	Page<OrderDetail> findAllOrderDetails(Pageable pageable);
	
	Optional<OrderDetail> findOrderDetailById(Integer orderDetailId);
	
	OrderDetail createOrderDetail(OrderDetail orderDetail);
	
	OrderDetail updateOrderDetail(OrderDetail orderDetail);
	
	void deleteOrderDetail(Integer orderDetailId);
}
