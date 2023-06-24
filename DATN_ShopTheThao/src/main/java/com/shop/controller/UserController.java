package com.shop.controller;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/info")
	public String getInfo(Model model) {
	    Account account = accountService.findAccountById("sinh").orElse(null);  
	    Customer customer = account.getCustomers().get(0);
	    Address address = customer.getAddress();
	  
	    model.addAttribute("account", account);
	    model.addAttribute("customer", customer);
	    model.addAttribute("address", address);
	    return "user-page/user-info";
	}

	@RequestMapping("/list-order")
	public String getListOrder() {
		Account account = accountService.findAccountById("kiet").orElse(null);
		return "user-page/list-order";
	}
}
