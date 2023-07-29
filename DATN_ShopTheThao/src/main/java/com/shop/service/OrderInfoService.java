package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.OrderInfo;

public interface OrderInfoService {
	
	Page<OrderInfo> findAllOrderInfo(Pageable pageable);

	List<OrderInfo> findAllOrderInfo();

	Optional<OrderInfo> findOrderInfoById(Integer orderInfoId);

	OrderInfo createOrderInfo(OrderInfo orderInfo);

	OrderInfo updateOrderInfo(OrderInfo orderInfo);

	void deleteOrderInfo(Integer orderInfoId);

}
