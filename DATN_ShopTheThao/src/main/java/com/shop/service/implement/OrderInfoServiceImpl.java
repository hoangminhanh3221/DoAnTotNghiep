package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.OrderDetail;
import com.shop.entity.OrderInfo;
import com.shop.repository.OrderInfoRepository;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderInfoService;

@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	
	private final OrderInfoRepository orderInfoRepository;
	
	public OrderInfoServiceImpl(OrderInfoRepository orderInfoRepository) {
		this.orderInfoRepository = orderInfoRepository;
	}

	@Override
	public Page<OrderInfo> findAllOrderInfo(Pageable pageable) {
		return orderInfoRepository.findAll(pageable);
	}

	@Override
	public List<OrderInfo> findAllOrderInfo() {
		return orderInfoRepository.findAll();
	}

	@Override
	public Optional<OrderInfo> findOrderInfoById(Integer orderInfoId) {
		return orderInfoRepository.findById(orderInfoId);
	}

	@Override
	public OrderInfo createOrderInfo(OrderInfo orderInfo) {
		return orderInfoRepository.save(orderInfo);
	}

	@Override
	public OrderInfo updateOrderInfo(OrderInfo orderInfo) {
		return orderInfoRepository.save(orderInfo);
	}

	@Override
	public void deleteOrderInfo(Integer orderInfoId) {
		orderInfoRepository.deleteById(orderInfoId);
		
	}

}
