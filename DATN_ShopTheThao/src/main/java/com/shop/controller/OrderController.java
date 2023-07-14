package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		
		return "user-page/checkout";
	}
	
}
