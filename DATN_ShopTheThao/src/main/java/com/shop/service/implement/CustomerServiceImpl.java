package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Customer;
import com.shop.repository.CustomerRepository;
import com.shop.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Page<Customer> findAllCustomer(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findCustomerById(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer findCustomerByUsername(String username) {
		return customerRepository.findCustomerByUsername(username).get(0);
	}

}
