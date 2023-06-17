package com.shop.service.implement;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Order;
import com.shop.entity.Payment;
import com.shop.repository.OrderDetailRepository;
import com.shop.repository.PaymentRepository;
import com.shop.service.PaymentService;

@Service
public class PaymentServicelmpl implements PaymentService {

	private final PaymentRepository paymentRepository;
	
	public PaymentServicelmpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@Override
	public Page<Payment> findAllPayments(Pageable pageable) {
		return paymentRepository.findAll(pageable);
	}

	@Override
	public List<Payment> findAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Optional<Payment> findPaymentById(Integer paymentId) {
		return paymentRepository.findById(paymentId);
	}

	@Override
	public Payment createPayment(String paymentMethod, Double paymentAmount, Date paymentDate, Boolean paymentStatus,
			Order order) {
		Payment payment = new Payment(paymentMethod,paymentAmount, paymentDate, paymentStatus, order);
		return paymentRepository.save(payment);
	}

	@Override
	public Payment updatePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public void deletePayment(Integer paymentId) {
		paymentRepository.deleteById(paymentId);
	}
}
