package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Color;
import com.shop.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
