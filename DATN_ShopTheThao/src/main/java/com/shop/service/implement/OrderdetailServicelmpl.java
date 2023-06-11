package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.OrderDetail;
import com.shop.repository.OrderDetailRepository;
import com.shop.service.OrderdetailService;

public class OrderdetailServicelmpl implements OrderdetailService{
	@Autowired
    private OrderDetailRepository orderDetailRepository;
	
    @Override
    public Page<OrderDetail> findAllOrderDetails(Pageable pageable) {
        return orderDetailRepository.findAll(pageable);
    }
    
    @Override
    public List<OrderDetail> findAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public Optional<OrderDetail> findOrderDetailById(Integer orderDetailId) {
        return orderDetailRepository.findById(orderDetailId);
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetail(Integer orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}
