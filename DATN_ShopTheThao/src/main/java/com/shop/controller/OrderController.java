package com.shop.controller;

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
import com.shop.util.AuthenticationFacade;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private AuthenticationFacade af;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
	    if (af.isAuthenticated()) {
	        Customer customer = customerService.findCustomerByUsername(af.getUsername());
	        model.addAttribute("customer", customer);
	    }
	    return "user-page/checkout";
	}
	
}
