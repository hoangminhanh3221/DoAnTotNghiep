package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.OrderInfo;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer>{

}
