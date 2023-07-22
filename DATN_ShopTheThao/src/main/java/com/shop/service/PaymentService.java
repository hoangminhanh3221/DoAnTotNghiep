package com.shop.service;

import com.shop.entity.Order;
import com.shop.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Page<Payment> findAllPayments(Pageable pageable);

    List<Payment> findAllPayments();

    Optional<Payment> findPaymentById(Integer paymentId);

<<<<<<< HEAD
    Payment createPayment(Payment payment);
=======
    Payment createPayment(String paymentMethod, Double paymentAmount, Date paymentDate, Boolean paymentStatus, Order order);
>>>>>>> update-entity

    Payment updatePayment(Payment payment);

    void deletePayment(Integer paymentId);
}
