package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entity.Account;
import com.shop.entity.Order;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderService;
import com.shop.util.XDate;

@RestController
public class OrderAPI {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private XDate xDate;

	@RequestMapping("/orderapi")
	public Order getOrder() {
		Account account = accountService.findAccountById("kiet").orElse(null);
		return account.getOrders().get(0);
	}
}
