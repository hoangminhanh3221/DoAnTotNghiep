package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Order;
import com.shop.service.OrderService;
import com.shop.service.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@GetMapping("/view")
	public String view() {
		return "user-page/shopping-cart";
	}
	
}
