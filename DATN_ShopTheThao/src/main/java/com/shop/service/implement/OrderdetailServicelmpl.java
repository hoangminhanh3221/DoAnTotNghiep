package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.OrderDetail;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderDetailService;

@Service
public class OrderdetailServicelmpl implements OrderDetailService{
	
	private final OrderDetailRepository orderDetailRepository;

    public OrderdetailServicelmpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
	
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
