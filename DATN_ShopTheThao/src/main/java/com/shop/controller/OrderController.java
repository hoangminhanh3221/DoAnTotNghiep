package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Order;
import com.shop.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/admin/order")
	public String admorder() {
		
		return "admin-page/order-list";
	}
	
	@GetMapping("/orders")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.findAllOrders();
        System.out.println(orders.get(0).get);
        model.addAttribute("orders", orders);
        return "admin-page/order-list"; // Trả về tên của trang HTML muốn hiển thị (ví dụ: orders.html)
    }
}