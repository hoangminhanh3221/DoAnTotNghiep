package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Customer;

public interface CustomerService {
	
	Page<Customer> findAllCustomer(Pageable pageable);
	
	List<Customer> findAllCustomer();
	
	Customer findCustomerByUsername(String username);

	Optional<Customer> findCustomerById(Integer id);
	
	Customer createCustomer(Customer customer);

	Customer updateCustomer(Customer customer);

	void deleteCustomer(Integer id);
}
