package com.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.entity.Account;
import com.shop.entity.Customer;
import java.util.List;


public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	List<Customer> findByAccount(Account account);
}
