package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.OrderService;

@Controller
public class OrderController {
	
//	@Autowired
//	OrderService orderService;
	
	@RequestMapping("/admin/order")
	public String admorder() {
		
		return "admin-page/order-list";
	}

}