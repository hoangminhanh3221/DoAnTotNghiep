package com.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.entity.Customer;

public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	
	@Query("SELECT c FROM Customer c WHERE c.account.username = :username")
	List<Customer> findCustomerByUsername(String username);
}
